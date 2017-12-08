package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "patient")
public class Patient extends Account {
  private String job;

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }
}
