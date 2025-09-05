package com.drona.precommit.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.drona.precommit.utils.LoggingUtils;

public class GitUtils {

    public static String getCurrentBranch() {
        LoggingUtils.debug("Getting current git branch");
        String branch = runSingleLine("git rev-parse --abbrev-ref HEAD");
        LoggingUtils.debug("Current branch: " + branch);
        return branch;
    }

    public static String extractJiraId(String branch) {
        LoggingUtils.debug("Extracting Jira ID from branch: " + branch);
        if (branch == null) {
            LoggingUtils.debug("Branch name is null, cannot extract Jira ID");
            return null;
        }
        String[] parts = branch.split("-");
        if (parts.length >= 2 && parts[0].matches("[A-Z]+") && parts[1].matches("\\d+")) {
            String jiraId = parts[0] + "-" + parts[1];
            LoggingUtils.debug("Extracted Jira ID: " + jiraId);
            return jiraId;
        }
        LoggingUtils.debug("No Jira ID found in branch name");
        return null;
    }

    public static String getStagedFilesSummary() {
        LoggingUtils.debug("Getting staged files summary");
        String list = runMultiLine("git diff --cached --name-only");
        if (list == null || list.isBlank()) {
            LoggingUtils.debug("No staged files found");
            return null;
        }
        String summary = "Updated " + list;
        LoggingUtils.debug("Staged files summary: " + summary);
        return summary;
    }

    private static String runSingleLine(String cmd) {
        try {
            LoggingUtils.debug("Running command: " + cmd);
            ProcessBuilder processBuilder = new ProcessBuilder(shell(), shellFlag(), cmd);
            processBuilder.redirectErrorStream(true);
            Process p = processBuilder.start();

            StringBuilder output = new StringBuilder();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    output.append(line);
                }
            }

            int exitCode = p.waitFor();
            LoggingUtils.debug("Command exit code: " + exitCode);
            LoggingUtils.debug("Command output: " + output.toString());

            if (exitCode != 0) {
                LoggingUtils.error("Command failed with exit code: " + exitCode);
                return null;
            }

            String result = output.toString().trim();
            LoggingUtils.debug("Final command result: " + result);
            return result;
        } catch (Exception e) {
            LoggingUtils.error("Error running command: " + cmd + ", error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String runMultiLine(String cmd) {
        try {
            LoggingUtils.debug("Running multi-line command: " + cmd);
            Process p = new ProcessBuilder(shell(), shellFlag(), cmd).redirectErrorStream(true).start();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    if (sb.length() > 0) sb.append(", ");
                    sb.append(line);
                }
                String result = sb.toString();
                LoggingUtils.debug("Multi-line command result: " + result);
                return result;
            }
        } catch (Exception e) {
            LoggingUtils.error("Error running multi-line command: " + cmd + ", error: " + e.getMessage());
            return null;
        }
    }

    private static String shell() {
        String os = System.getProperty("os.name").toLowerCase();
        String shell = os.contains("win") ? "cmd.exe" : "bash";
        LoggingUtils.debug("Determined shell: " + shell + " based on OS: " + os);
        return shell;
    }

    private static String shellFlag() {
        String os = System.getProperty("os.name").toLowerCase();
        String flag = os.contains("win") ? "/c" : "-c";
        LoggingUtils.debug("Determined shell flag: " + flag + " based on OS: " + os);
        return flag;
    }
}