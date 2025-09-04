package com.drona.precommit.service;

import com.drona.precommit.utils.GitUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class CommitMessageTool {

    @Tool(description = "Generate commit message based on git branch and staged changes")
    public String generateCommitMessage() {
        String branch = GitUtils.getCurrentBranch();
        String jiraId = GitUtils.extractJiraId(branch);
        if (jiraId == null || jiraId.isBlank()) {
            return "ERROR: No Jira ID found in branch name.";
        }
        String changes = GitUtils.getStagedFilesSummary();
        if (changes == null || changes.isBlank()) {
            return jiraId + " - Code changes";
        }
        return jiraId + " - " + changes;
    }

    @Tool(description = "Generate commit message with a custom summary")
    public String generateCustomCommitMessage(String summary) {
        String branch = GitUtils.getCurrentBranch();
        String jiraId = GitUtils.extractJiraId(branch);
        if (jiraId == null || jiraId.isBlank()) {
            return "ERROR: No Jira ID found in branch name.";
        }
        if (summary == null || summary.isBlank()) {
            return "ERROR: Summary cannot be empty.";
        }
        return jiraId + " - " + summary.trim();
    }
}
