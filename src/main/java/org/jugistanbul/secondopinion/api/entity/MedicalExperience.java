package org.jugistanbul.secondopinion.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MedicalExperience {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Expertise expertise;
	@OneToOne
	private Hospital hospital;
	@OneToOne
	private City city;
	private Date startDate;
	private Date endDate;

	@ManyToOne
	@JsonIgnore
	private Medic medic;
	
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
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Medic getMedic() {
		return medic;
	}
	public void setMedic(Medic medic) {
		this.medic = medic;
	}
}
