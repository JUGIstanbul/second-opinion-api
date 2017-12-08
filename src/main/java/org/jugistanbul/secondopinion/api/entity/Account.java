package org.jugistanbul.secondopinion.api.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private Date birthDate;
	private String gender;
	private LocalDate lastLogin;
	private String addictiveDrugProfile;
	private String alcoholConsumptionProfile;
	private String smokerProfile;

	public Account(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDate lastLogin) {
		this.lastLogin = lastLogin;
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
