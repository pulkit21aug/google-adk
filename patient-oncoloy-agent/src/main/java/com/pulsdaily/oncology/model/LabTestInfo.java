package com.pulsdaily.oncology.model;

public class LabTestInfo {

    private String testName;
    private String testResult;
    private String testDate;
    private String minValue;
    private String maxValue;
    private String labInterpretation;

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getLabInterpretation() {
        return labInterpretation;
    }

    public void setLabInterpretation(String labInterpretation) {
        this.labInterpretation = labInterpretation;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
