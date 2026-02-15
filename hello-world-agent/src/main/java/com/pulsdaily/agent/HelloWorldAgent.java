package com.pulsdaily.agent;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class HelloWorldAgent {

    public static BaseAgent ROOT_AGENT =  initAgent();

    public static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("hello-world-agent")
                .description("Greets the user with a message")
                .instruction("""
                        You are a helpful assistant that greets the user.
                        """)
                .model("gemini-2.5-flash")
                .tools(FunctionTool.create(HelloWorldAgent.class, "greetUser"))
                .build();
    }




    /**
     * Mock tool implementation
     */
    @Annotations.Schema(description = "Greet the user")
    public static Map<String, String> greetUser(
            @Annotations.Schema(name = "PulsDaily", description = "Greet the user") String name) {
        return Map.of(
                "name", name,
                "greeting", "Namaste Aap Kaise Hai ."
        );
    }
}
