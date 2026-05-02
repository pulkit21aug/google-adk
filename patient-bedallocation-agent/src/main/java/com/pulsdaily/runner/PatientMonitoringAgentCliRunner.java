package com.pulsdaily.runner;

import com.google.adk.runner.InMemoryRunner;
import com.google.adk.sessions.Session;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import com.pulsdaily.agent.PatientBedallocationAgent;
import io.reactivex.rxjava3.core.Flowable;
import com.google.adk.events.Event;
import com.google.adk.agents.RunConfig;

/**
 * This class will run the sequential agents
 */
public class PatientMonitoringAgentCliRunner {

    private static final String userId = "pulsdaily";

    public static void main(String[] args) {

        //create inmemory runner
        InMemoryRunner runner = new InMemoryRunner(PatientBedallocationAgent.getSequentialAgent(), PatientBedallocationAgent.APP_NAME);

        //In Memory runner creates a session sercice which we can use to create session for our agent
        Session session = runner.sessionService().createSession(runner.appName(), userId).blockingGet();

        //build runconfig
        RunConfig runConfig = RunConfig.builder().build();

        //run the agent
        Flowable<Event> events = runner.runAsync(session.userId(), session.id(),
                Content.fromParts(Part.fromText("Allocate beds to patients")), runConfig);

        events.blockingForEach(event -> {
            if (event.finalResponse()) {
                System.out.println(event.stringifyContent());
            }
        });
    }
}
