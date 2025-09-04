package com.drona.precommit.toolconfig;

import com.drona.precommit.service.CommitMessageTool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;

@Configuration
public class ToolConfig {

    private final CommitMessageTool commitMessageTool;

    @Autowired
    public ToolConfig(CommitMessageTool commitMessageTool) {
        this.commitMessageTool = commitMessageTool;
    }

    @Bean
    public ToolCallbackProvider toolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(commitMessageTool)
                .build();
    }
}
