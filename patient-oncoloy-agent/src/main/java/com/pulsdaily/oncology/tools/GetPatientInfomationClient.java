package com.pulsdaily.oncology.tools;

import com.google.adk.tools.Annotations;
import com.pulsdaily.oncology.model.PatientInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pulsdaily
 * @date 2024/6/17
 * This class is to fetch patient information from patient information service
 */
@Service
public class GetPatientInfomationClient {

    @Autowired
    private static RestTemplate restTemplate;

    @Annotations.Schema(description = "Fetch patient information from patient information service")
    public static PatientInfo getPatientInformation(@Annotations.Schema(name ="patiendId",description = "patient identifier") String patientId) {
        // REST client to fetch patient information from patient information service
        String url = "http://localhost:8070/patients/" + patientId; // Replace with actual service URL
        try {
            return restTemplate.getForObject(url, PatientInfo.class);
        } catch (Exception e) {
            // Handle exceptions (e.g., service unavailable)
            return null; // Or throw a custom exception
        }
    }
}
