package com.pulsdaily.agent;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations;
import com.google.adk.tools.FunctionTool;
import com.pulsdaily.tools.AttendantNotificationService;

import java.util.Map;

/**
 * @author Pulkit Saxena
 * @since 2024-06-17
 * This is streaming agent which will monitor critical vital signs of patients in real-time and provide alerts and recommendations
 * to healthcare providers based on the data received from various medical devices and sensors.
 * The agent will be designed to analyze the incoming data, identify any abnormalities or critical conditions, and
 * generate actionable insights to assist healthcare providers in making informed decisions for patient care.
 * The agent will be able to integrate with various medical devices and sensors, such as heart rate monitors,
 * blood pressure monitors, and oxygen saturation monitors, to collect real-time data on patients' vital signs.
 * The agent will also be able to provide alerts to patients attendant.
 */
public class PatientMonitoringAgent {

    public static BaseAgent ROOT_AGENT =  initAgent();

    public static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("Patient-Monitoring-Agent")
                .description("Sends notification to patient's attendant")
                .instruction("""
                        You are a patient monitoring agent.Will understand the vitals of patient and send notification to
                        patient's attedant .If the vitals of the patient are OK  then notify patient attendant with message
                        as I am stable and in good hands.If vitals are not OK then notify patient attendant with message as I am not feeling well and need your help.
                        Please come to hospital as soon as possible.
                        """)
                .model("gemini-2.5-flash-native-audio-preview-12-2025")
                .tools(FunctionTool.create(AttendantNotificationService.class, "sendNotification"))
                .build();
    }


}
