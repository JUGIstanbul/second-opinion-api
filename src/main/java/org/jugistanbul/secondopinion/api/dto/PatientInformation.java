package org.jugistanbul.secondopinion.api.dto;

import java.util.List;
import java.util.Set;
import org.jugistanbul.secondopinion.api.entity.PastChronicDisease;
import org.jugistanbul.secondopinion.api.entity.PastMedicine;
import org.jugistanbul.secondopinion.api.entity.PastOperation;
import org.jugistanbul.secondopinion.api.types.Gender;

public class PatientInformation {

  private String username;
  private String password;
  private String email;
  private String phone;
  private Gender gender;
  private String address;
  private String birthday;
  private String job;
  private String alcoholConsumptionProfile;
  private String smokerProfile;
  private String addictiveDrugProfile;
  private Set<PastChronicDisease> chronicDiseases;
  private Set<PastOperation> pastOperations;
  private Set<PastMedicine> medications;

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone() {
    return phone;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getAlcoholConsumptionProfile() {
    return alcoholConsumptionProfile;
  }

  public void setAlcoholConsumptionProfile(String alcoholConsumptionProfile) {
    this.alcoholConsumptionProfile = alcoholConsumptionProfile;
  }

  public String getSmokerProfile() {
    return smokerProfile;
  }

  public void setSmokerProfile(String smokerProfile) {
    this.smokerProfile = smokerProfile;
  }

  public String getAddictiveDrugProfile() {
    return addictiveDrugProfile;
  }

  public void setAddictiveDrugProfile(String addictiveDrugProfile) {
    this.addictiveDrugProfile = addictiveDrugProfile;
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
