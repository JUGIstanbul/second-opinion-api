package org.jugistanbul.secondopinion.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "patient")
public class Patient extends EntityBase {

    private String userName;
    
    private String password;
    
    private String email;
    
    private String phoneNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
}
