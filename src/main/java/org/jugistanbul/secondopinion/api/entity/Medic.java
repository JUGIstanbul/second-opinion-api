package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;

@Entity
public class Medic extends Account {
	private String fullname;
	private String resumeLink;
	private String licenseNumber;
	private String refferalCode;
	private String currentTitle;

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

	private long reputationPoints;

}
