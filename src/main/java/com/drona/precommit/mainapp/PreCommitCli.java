package com.drona.precommit.mainapp;

import com.drona.precommit.service.CommitMessageTool;
import com.drona.precommit.utils.LoggingUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PreCommitCli {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(PreCommitApplication.class)) {

            CommitMessageTool tool = ctx.getBean(CommitMessageTool.class);
            
            LoggingUtils.info("PreCommitCli started with " + args.length + " arguments");

            String result;
            if (args.length == 0) {
                LoggingUtils.info("Generating commit message with staged changes");
                result = tool.generateCommitMessage();
            } else {
                LoggingUtils.info("Generating commit message with custom summary");
                result = tool.generateCustomCommitMessage(String.join(" ", args));
            }
            
            LoggingUtils.info("Commit message generated successfully: " + result);
            System.out.println(result);
        } catch (Exception e) {
            LoggingUtils.error("Error in PreCommitCli: " + e.getMessage());
            e.printStackTrace();
        }
    }
}