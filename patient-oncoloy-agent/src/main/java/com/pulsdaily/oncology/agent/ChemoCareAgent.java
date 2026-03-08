package com.pulsdaily.oncology.agent;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;

import com.google.adk.tools.FunctionTool;

import com.pulsdaily.oncology.tools.GetPatientInfomationClient;
import com.pulsdaily.oncology.tools.ProcessLabReports;


/**
 * @author pulsdaily
 * @date 2024/6/17
 * This class is the main agent class for chemotherapy care, it will use the tools to fetch patient information and lab test information, and then generate care plan for chemotherapy patients.
 */
public class ChemoCareAgent {


    private static String NAME = "ChemoCareAgent";

    public static  BaseAgent ROOT_AGENT = initAgent();

    public static BaseAgent initAgent() {
        return LlmAgent.builder().name(NAME).model("gemini-2.0-flash")
                .description("This agent is responsible for generating care plan for chemotherapy patients based on patient information and lab test information.")
                .instruction("You are a helpful assistant for oncology care, you will use the tools to fetch patient information and lab test information, " +
                        "and then generate care plan for chemotherapy patients. " +
                        "The care plan should include the following sections: 1. Patient Information: Include basic information such as name" +
                        "lab results. If  the test report is abnormal, provide interpretation to doctor" +
                        "Confirm from doctor to cancel the admission and  inform adminstration desk for reschedule the appointment after few days with lab test report"
                )
                .tools(FunctionTool.create(GetPatientInfomationClient.class, "getPatientInformation"),
                        FunctionTool.create(ProcessLabReports.class, "getLabTestInfo"))
                .build();
    }


}
