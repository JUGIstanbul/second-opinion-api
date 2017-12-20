package org.jugistanbul.secondopinion.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PastChronicDisease {

  @Id
  @GeneratedValue
  private long id;

  private String diagnosis;
  private int year;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDiagnosis() {
    return diagnosis;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }


}
