package org.jugistanbul.secondopinion.api.dto;

public class PatientResponse extends Response {

    private String patientId;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
