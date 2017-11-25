package org.jugistanbul.secondopinion.api.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class Case {

    private Patient patient;
    private String nickname;
    private LocalDate illnessStartDate;
    private String symptoms;
    private String note;
    private Set<Treatment> treatments = new HashSet<>();
    private Set<Medicine> medicine = new HashSet<>();
    private CaseStatus status;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getIllnessStartDate() {
        return illnessStartDate;
    }

    public void setIllnessStartDate(LocalDate illnessStartDate) {
        this.illnessStartDate = illnessStartDate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Set<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(Set<Medicine> medicine) {
        this.medicine = medicine;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }
}
