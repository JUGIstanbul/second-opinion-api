package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient extends Account {

	private String job;
	private String addictiveDrugProfile;
	private String alcoholConsumptionProfile;
	private String smokerProfile;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddictiveDrugProfile() {
		return addictiveDrugProfile;
	}

	public void setAddictiveDrugProfile(String addictiveDrugProfile) {
		this.addictiveDrugProfile = addictiveDrugProfile;
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
	
	
}
