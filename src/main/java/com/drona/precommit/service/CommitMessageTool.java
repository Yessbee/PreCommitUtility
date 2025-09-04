package com.drona.precommit.service;

import com.drona.precommit.mainapp.PreCommitApplication;
import com.drona.precommit.utils.GitUtils;
import com.drona.precommit.utils.LoggingUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class CommitMessageTool {


    @Tool(description = "Generate commit message based on git branch and staged changes")
    public String generateCommitMessage() {
        LoggingUtils.info("Generating commit message based on git branch and staged changes");
        String branch = GitUtils.getCurrentBranch();
        LoggingUtils.info("Current branch: " + branch);
        String jiraId = GitUtils.extractJiraId(branch);
        if (jiraId == null || jiraId.isBlank()) {
            LoggingUtils.warn("No Jira ID found in branch name: " + branch);
            return "ERROR: No Jira ID found in branch name.";
        }
        String changes = GitUtils.getStagedFilesSummary();
        LoggingUtils.debug("Staged files summary: " + changes);
        if (changes == null || changes.isBlank()) {
            LoggingUtils.info("No staged changes found, using default message");
            return jiraId + " - Code changes";
        }
        LoggingUtils.info("Successfully generated commit message with staged changes");
        return jiraId + " - " + changes;
    }

    @Tool(description = "Generate commit message with a custom summary")
    public String generateCustomCommitMessage(String summary) {
        LoggingUtils.info("Generating commit message with custom summary");
        String branch = GitUtils.getCurrentBranch();
        LoggingUtils.debug("Current branch: " + branch);
        String jiraId = GitUtils.extractJiraId(branch);
        if (jiraId == null || jiraId.isBlank()) {
            LoggingUtils.warn("No Jira ID found in branch name: " + branch);
            return "ERROR: No Jira ID found in branch name.";
        }
        if (summary == null || summary.isBlank()) {
            LoggingUtils.warn("Summary cannot be empty");
            return "ERROR: Summary cannot be empty.";
        }
        LoggingUtils.info("Successfully generated commit message with custom summary");
        return jiraId + " - " + summary.trim();
    }
    
    @Tool(description = "Stop the MCP server")
    public String stopServer() {
        LoggingUtils.info("Received request to stop the server");
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Give time for the response to be sent
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            PreCommitApplication.stopServer();
        }).start();
        return "Server shutdown initiated. Goodbye!";
    }
}