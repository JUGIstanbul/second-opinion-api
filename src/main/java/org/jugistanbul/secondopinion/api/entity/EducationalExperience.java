package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EducationalExperience {
	@Id
	@GeneratedValue
	private long id;
	private Expertise expertise;
	private University university;
	private int gradYear;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Expertise getExpertise() {
		return expertise;
	}
	public void setExpertise(Expertise expertise) {
		this.expertise = expertise;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public int getGradYear() {
		return gradYear;
	}
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
}
