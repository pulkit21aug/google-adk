package com.pulsdaily.tools;

import com.google.adk.tools.Annotations;
import org.springframework.stereotype.Service;

/**
 * @author Pulkit Saxena
 * @since 2024-06-17
 * This is a tool which will be used by the agent to send notifications to patient's attendant in case of any critical conditions or abnormalities detected in patient's vital signs.
 * The tool will be designed to integrate with various notification systems, such as SMS, email, or push notifications, to ensure that the attendant receives timely alerts and updates about the patient's condition.
 * The tool will also be able to provide recommendations to the attendant on how to respond to the alerts and what actions to take in case of emergencies.
 */
@Service
public class AttendantNotificationService {

    @Annotations.Schema(description = "Sends notification to patient's attendant")
    public static void sendNotification(@Annotations.Schema(name = "attendantContact",description = "attedend of patient") String attendantContact,
                                        @Annotations.Schema(name = "message" ,description = "message for patient attemdant") String message) {
         // Implement the logic to send notification to the attendant using the provided contact information and message.
         // This could involve integrating with an SMS gateway, email service, or push notification service.
        System.out.println(attendantContact+"- "+message);
     }
}
