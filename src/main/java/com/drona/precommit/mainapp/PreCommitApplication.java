package com.drona.precommit.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.drona.precommit.utils.LoggingUtils;

@SpringBootApplication
public class PreCommitApplication {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        LoggingUtils.info("Starting MCP Server application ...");
        applicationContext = SpringApplication.run(PreCommitApplication.class, args);
        LoggingUtils.info("Application MCP Server started successfully");
    }

    public static void stopServer() {
        if (applicationContext != null) {
            LoggingUtils.info("Stopping MCP Server application ...");
            applicationContext.close();
            LoggingUtils.info("Application MCP Server stopped successfully");
        }
    }
}