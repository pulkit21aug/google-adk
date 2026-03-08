package com.pulsdaily.oncology.runner;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.RunConfig;
import com.google.adk.events.Event;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import com.pulsdaily.oncology.agent.ChemoCareAgent;
import io.reactivex.rxjava3.core.Flowable;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ChemoCareAgentRunner {


    public static void main(String[] args) throws Exception {

        {
            //build runconfig
            RunConfig runConfig = RunConfig.builder().build();
            InMemoryRunner runner = new InMemoryRunner(ChemoCareAgent.ROOT_AGENT);

            Session session = runner.sessionService().createSession(runner.appName(), "pulkit saxena").blockingGet();

            try (Scanner scanner = new Scanner(System.in, UTF_8.name())) {
                while (true) {
                    System.out.print("\nYou > ");

                    String userInput = scanner.nextLine();
                    if ("exit" .equalsIgnoreCase(userInput)) {
                        break;
                    }

                    Content userMsg = Content.fromParts(Part.fromText(userInput));
                    Flowable<Event> events = runner.runAsync(session.userId(), session.id(), userMsg, runConfig);

                    System.out.print("\nAgent > ");
                    events.blockingForEach(event -> {
                        if (event.finalResponse()) {
                            System.out.println(event.stringifyContent());
                        }
                    });
                }

            }

        }
    }


}
