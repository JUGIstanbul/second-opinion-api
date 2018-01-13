package org.jugistanbul.secondopinion.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PastMedicine {
  @Id
  @GeneratedValue
  private long id;

  private String medicineName;
  private int yearStarted;
  private int yearEnded;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMedicineName() {
    return medicineName;
  }

  public void setMedicineName(String medicineName) {
    this.medicineName = medicineName;
  }

  public int getYearStarted() {
    return yearStarted;
  }

  public void setYearStarted(int yearStarted) {
    this.yearStarted = yearStarted;
  }

  public int getYearEnded() {
    return yearEnded;
  }

  public void setYearEnded(int yearEnded) {
    this.yearEnded = yearEnded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PastMedicine that = (PastMedicine) o;

    if (id != that.id) return false;
    if (yearStarted != that.yearStarted) return false;
    if (yearEnded != that.yearEnded) return false;
    return medicineName.equals(that.medicineName);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + medicineName.hashCode();
    result = 31 * result + yearStarted;
    result = 31 * result + yearEnded;
    return result;
  }
}
