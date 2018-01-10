package org.jugistanbul.secondopinion.api.types;

public enum Gender {
	MALE, FEMALE;
	
	public static Gender toGender(String genderValue) {
		return Gender.MALE.name().equalsIgnoreCase(genderValue)?MALE:FEMALE;
	}
}
