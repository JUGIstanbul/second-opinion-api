package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "medical_case")
public class Case extends EntityBase {

    @ManyToOne
    private Patient patient;

    @Column
    private String nickname;

    @Column
    private LocalDate illnessStartDate;

    @Column
    private String symptoms;

    @Column
    private String note;

    @OneToMany
    @JoinColumn(name = "case_id")
    private Set<Treatment> treatments = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "case_id")
    private Set<Medicine> medicine = new HashSet<>();

    @Column
    @Enumerated(EnumType.STRING)
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
