package com.pulsdaily.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.SequentialAgent;

import java.util.List;

public class PatientBedallocationAgent {

    public static final String APP_NAME = "Patient-Bedallocation-Agent";

    public static SequentialAgent getSequentialAgent(){
        SequentialAgent agent = SequentialAgent.builder().name(APP_NAME)
                .description("This agent will allocate beds to patients in the hospital")
                .subAgents(List.of(PatientFetchAgent.getPatientFetchAgent(), CheckAvailableBedsAgent.getAvailBedsAgent()))
                .build();

        return agent;
    }

}
