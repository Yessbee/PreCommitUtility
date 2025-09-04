package com.drona.precommit.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitUtils {

    public static String getCurrentBranch() {
        return runSingleLine("git rev-parse --abbrev-ref HEAD");
    }

    public static String extractJiraId(String branch) {
        if (branch == null) return null;
        String[] parts = branch.split("-");
        if (parts.length >= 2 && parts[0].matches("[A-Z]+") && parts[1].matches("\\d+")) {
            return parts[0] + "-" + parts[1];
        }
        return null;
    }

    public static String getStagedFilesSummary() {
        String list = runMultiLine("git diff --cached --name-only");
        if (list == null || list.isBlank()) return null;
        return "Updated " + list;
    }

    private static String runSingleLine(String cmd) {
        try {
            Process p = new ProcessBuilder(shell(), shellFlag(), cmd).redirectErrorStream(true).start();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                return r.readLine();
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String runMultiLine(String cmd) {
        try {
            Process p = new ProcessBuilder(shell(), shellFlag(), cmd).redirectErrorStream(true).start();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    if (sb.length() > 0) sb.append(", ");
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static String shell() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? "cmd.exe" : "bash";
    }

    private static String shellFlag() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? "/c" : "-c";
    }
}
