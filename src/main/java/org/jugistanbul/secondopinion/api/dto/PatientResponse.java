package org.jugistanbul.secondopinion.api.dto;

public class PatientResponse extends Response {

    private long patientId;

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
