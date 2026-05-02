package com.pulsdaily.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This agent will check for available beds in  the  hospital.
 * We will have a mock implementation to fetch beds
 */
public class CheckAvailableBedsAgent {

   public static LlmAgent getAvailBedsAgent(){
      LlmAgent availBedAgent = LlmAgent.builder().name("Check-Available-Beds-Agent")
              .description("Checks for available beds in the hospital")
              .instruction("""
                        You are a check available beds agent. Your task is to check for available beds in the hospital.
                      """)
              .model("gemini-2.0-flash")
              .tools(FunctionTool.create(CheckAvailableBedsAgent.class, "checkAvailableBeds"))
              .build();

       return availBedAgent;
   }

    @Annotations.Schema(description = "Check available beds for patients")
    public static Map<String,String> checkAvailableBeds(@Annotations.Schema(name = "patientList", description = "List of patients to allocate beds") List<String> patientList){
       Map<String,String> map = new HashMap<>();

       for(String patient : patientList){
          map.put(patient,"bed"+patient.charAt(patient.length()-1));
        }
        return map;
    }

}
