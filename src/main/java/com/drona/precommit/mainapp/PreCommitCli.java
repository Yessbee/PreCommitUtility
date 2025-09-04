package com.drona.precommit.mainapp;

import com.drona.precommit.service.CommitMessageTool;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PreCommitCli {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(PreCommitApplication.class)) {

            CommitMessageTool tool = ctx.getBean(CommitMessageTool.class);

            String result;
            if (args.length == 0) {
                result = tool.generateCommitMessage();
            } else {
                result = tool.generateCustomCommitMessage(String.join(" ", args));
            }

            System.out.println(result);
        }
    }
}
