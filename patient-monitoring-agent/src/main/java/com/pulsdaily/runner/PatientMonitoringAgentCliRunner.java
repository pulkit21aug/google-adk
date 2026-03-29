package com.pulsdaily.runner;

import com.google.adk.agents.LiveRequestQueue;
import com.google.adk.agents.RunConfig;
import com.google.adk.events.Event;
import com.google.adk.runner.InMemoryRunner;
import com.google.adk.runner.Runner;
import com.google.adk.sessions.InMemorySessionService;
import com.google.adk.sessions.Session;
import com.google.genai.types.Blob;
import com.google.genai.types.Content;
import com.google.genai.types.Part;

import com.pulsdaily.agent.PatientMonitoringAgent;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PatientMonitoringAgentCliRunner {

    private String userId;
    private String sessionId;
    private Runner runner;

    public PatientMonitoringAgentCliRunner(String userId) {
        this.userId = userId;
        this.sessionId = UUID.randomUUID().toString();
        String appName = "Patient-Monitoring-Agent";

        InMemorySessionService sessionService = new InMemorySessionService();
        this.runner = new Runner(PatientMonitoringAgent.ROOT_AGENT, appName, null, sessionService);

//        ConcurrentMap<String, Object> initialState = new ConcurrentHashMap<>();
//        var unused =
//                sessionService.createSession(appName, userId, initialState, sessionId).blockingGet();
    }

    private void monitorVitals() {
        System.out.println("Monitoring patient's vitals in real-time and sending notifications to attendant in case of" +
                " any critical conditions or abnormalities detected in patient's vital signs...");

        RunConfig runConfig = RunConfig.builder().setStreamingMode(RunConfig.StreamingMode.BIDI).build();
        LiveRequestQueue liveRequestQueue = new LiveRequestQueue();

        Flowable<Event> eventStream = this.runner.runLive(runner.sessionService().createSession(this.userId, this.sessionId).blockingGet(),
                liveRequestQueue, runConfig);

        AtomicBoolean isRunning = new AtomicBoolean(true);
        AtomicBoolean conversationEnded = new AtomicBoolean(false);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 3. SUBSCRIBE TO THE OUTPUT (The Missing Piece)
        // Without this, the agent sends events but nobody listens.
        Disposable subscription = eventStream.subscribe(
                event -> {
                    // Handle events (Agent responses, tool calls, etc.)
                    System.out.println("\n[AGENT ALERT]: " + event.toString());
                },
                throwable -> {
                    System.err.println("\n[STREAM ERROR]: " + throwable.getMessage());
                },
                () -> {
                    System.out.println("\n[STREAM COMPLETED]");
                }
        );

        //Task for capturing vitals
        executorService.submit(() -> {
            while (isRunning.get()) {
                // Simulate capturing vitals and sending them to the agent
                String vitalsData = "Attendant:Pulsdaily ,Heart Rate: 80 bpm, Blood Pressure: 120/80 mmHg, Oxygen Saturation: 98%";
                Content content = Content.builder()
                        .parts(List.of(Part.builder().text(vitalsData).build()))
                        .build();

                liveRequestQueue.content(content);
                try {
                    Thread.sleep(5000); // Simulate delay in capturing vitals
                } catch (InterruptedException e) {
                    System.err.println("Monitoring interrupted: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public static void main(String[] args) {
        PatientMonitoringAgentCliRunner cliRunner = new PatientMonitoringAgentCliRunner("pulkit saxena");
        cliRunner.monitorVitals();
        System.out.println("Monitoring patient's vitals in real-time and sending notifications to attendant in case of " +
                "any critical conditions or abnormalities detected in patient's vital signs...");
        System.out.println("System is running. Press Ctrl+C to stop.");
    }


}
