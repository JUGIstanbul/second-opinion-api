package org.jugistanbul.secondopinion.api.types;

public enum Gender {
	MALE, FEMALE;
	
	public static Gender toGender(String genderValue) {
		return genderValue.equalsIgnoreCase(Gender.MALE.toString())?MALE:FEMALE;
	}
}
