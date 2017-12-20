package org.jugistanbul.secondopinion.api.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "patient")
public class Patient extends Account {

  private String job;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<PastChronicDisease> chronicDiseases;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<PastOperation> pastOperations;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<PastMedicine> medications;

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Set<PastChronicDisease> getChronicDiseases() {
    return chronicDiseases;
  }

  public void setChronicDiseases(
      Set<PastChronicDisease> chronicDiseases) {
    this.chronicDiseases = chronicDiseases;
  }

  public Set<PastOperation> getPastOperations() {
    return pastOperations;
  }

  public void setPastOperations(
      Set<PastOperation> pastOperations) {
    this.pastOperations = pastOperations;
  }

  public Set<PastMedicine> getMedications() {
    return medications;
  }

  public void setMedications(
      Set<PastMedicine> medications) {
    this.medications = medications;
  }
}
