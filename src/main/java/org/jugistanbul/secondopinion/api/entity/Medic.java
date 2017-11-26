package org.jugistanbul.secondopinion.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Medic extends Account {
	private String fullname;
	private String resumeLink;
	private String licenseNumber;
	private String refferalCode;
	private String currentTitle;
	private long reputationPoints;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "medic", cascade = CascadeType.ALL)
	private Set<EducationalExperience> educationalExperiences;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "medic", cascade = CascadeType.ALL)
	private Set<MedicalExperience> medicalExperiences;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getResumeLink() {
		return resumeLink;
	}

	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getRefferalCode() {
		return refferalCode;
	}

	public void setRefferalCode(String refferalCode) {
		this.refferalCode = refferalCode;
	}

	public String getCurrentTitle() {
		return currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public long getReputationPoints() {
		return reputationPoints;
	}

	public void setReputationPoints(long reputationPoints) {
		this.reputationPoints = reputationPoints;
	}

	public Set<EducationalExperience> getEducationalExperiences() {
		return educationalExperiences;
	}

	public void setEducationalExperiences(Set<EducationalExperience> educationalExperiences) {
		this.educationalExperiences = educationalExperiences;
	}

	public Set<MedicalExperience> getMedicalExperiences() {
		return medicalExperiences;
	}

	public void setMedicalExperiences(Set<MedicalExperience> medicalExperiences) {
		this.medicalExperiences = medicalExperiences;
	}

}
