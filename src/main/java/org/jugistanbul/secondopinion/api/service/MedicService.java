package org.jugistanbul.secondopinion.api.service;

import java.util.List;

import org.jugistanbul.secondopinion.api.entity.EducationalExperience;
import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.entity.MedicalExperience;
import org.jugistanbul.secondopinion.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicService {
	@Autowired
	public MedicRepository medicRepository;
	
	public Medic findOne(Long id){
		return medicRepository.findOne(id);
	}
	
	public List<Medic> findAll() {
		return (List<Medic>) medicRepository.findAll();
	}
	
	public Medic save(Medic medic) {
		for (EducationalExperience educationalExperience : medic.getEducationalExperiences()) {
			educationalExperience.setMedic(medic);
		}
		for (MedicalExperience medicalExperience : medic.getMedicalExperiences()) {
			medicalExperience.setMedic(medic);
		}
		
		Medic newMedic = medicRepository.save(medic);
		
		return newMedic;
	}
}
