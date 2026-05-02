package com.pulsdaily.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;

import java.util.List;


/**
 * This agent will be responsible for fetching list of patients to whom the beds needs to be allocaed.
 * In this example we will mock the implementation to understand the concepts
 */
public class PatientFetchAgent {

    public static LlmAgent getPatientFetchAgent() {

        LlmAgent patientFetchAgent = LlmAgent.builder()
                .name("Patient-Fetch-Agent")
                .description("Fetches list of patients to whom the beds needs to be allocated")
                .instruction("""
                          You are a patient fetch agent. Your task is to fetch list of patients to whom the beds needs to be allocated.
                        """)
                .model("gemini-2.0-flash")
                .tools(FunctionTool.create(PatientFetchAgent.class, "fetchPatientsToAllocateBeds"))
                .build();

        return patientFetchAgent;
    }

    @Annotations.Schema(description = "Fetch patients to allocate beds")
    public static List<String> fetchPatientsToAllocateBeds() {
        // Mock implementation to return list of patients
        return List.of("Patient-A", "Patient-B", "Patient-C");
    }
}
