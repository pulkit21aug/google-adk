package com.pulsdaily.oncology.tools;

import com.google.adk.tools.Annotations;
import com.pulsdaily.oncology.model.LabTestInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


@Component
public class ProcessLabReports {

    @Annotations.Schema(description = "Fetch lab test information for a patient")
    public static Map<String,LabTestInfo> getLabTestInfo(@Annotations.Schema(name = "labId",description = "lab test identifier") String labId,
                                                         @Annotations.Schema(name = "PatientId",description = "Patient Identifier") String PatientId){
       return getMockLabTestInfo("mock-labId",PatientId);
    }

    private static Map<String, LabTestInfo> getMockLabTestInfo(String labId, String PatientId){
        // This is a mock implementation, replace with actual logic to fetch lab test info
        LabTestInfo labTestInfo = new LabTestInfo();
        labTestInfo.setTestName("Total Leukocyte Count(TLC)");
        labTestInfo.setTestResult("2");
        labTestInfo.setTestDate("2024-06-17");
        labTestInfo.setMinValue("4");
        labTestInfo.setMaxValue("10.0");
        labTestInfo.setLabInterpretation("Outside normal range, indicates leukopenia, which can be caused by chemotherapy or bone marrow suppression. " +
                "Monitor closely and consider supportive care if symptoms develop.");

        return Map.of(labId,labTestInfo);
    }
}
