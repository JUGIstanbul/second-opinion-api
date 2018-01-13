package org.jugistanbul.secondopinion.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseMockitoTest;
import org.jugistanbul.secondopinion.api.entity.Medic;
import org.jugistanbul.secondopinion.api.repository.MedicRepository;
import org.jugistanbul.secondopinion.api.types.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MedicServiceTest extends BaseMockitoTest{

	@InjectMocks
	private MedicService medicService;
	
	@Mock
	public MedicRepository medicRepository;
	
	public Medic medic;
	
	@Before 
	public void before(){
		medic = new Medic();
		medic.setId(1L);
		medic.setAddress("address");
		medic.setBirthDate(new Date());
		medic.setCurrentTitle("title");
		medic.setEmail("email");
		medic.setFullname("fullname");
		medic.setGender(Gender.MALE);
		medic.setLicenseNumber("licenseNumber");
		medic.setResumeLink("resumeLink");
	}
	
	@Test
	public void should_find_a_specific_medic(){
		
		when(medicRepository.findOne(1L)).thenReturn(medic);
		
		Medic foundMedic = medicService.findOne(1L);
		
		assertThat(foundMedic).isNotNull();
		assertThat(foundMedic.getId()).isEqualTo(1L);
		assertThat(foundMedic.getAddress()).isEqualTo("address");
		assertThat(foundMedic.getBirthDate()).isNotNull();
		assertThat(foundMedic.getCurrentTitle()).isEqualTo("title");
		assertThat(foundMedic.getEmail()).isEqualTo("email");
		assertThat(foundMedic.getFullname()).isEqualTo("fullname");
		assertThat(foundMedic.getGender().toString()).isEqualTo("MALE");
	}
	
	@Test
	public void should_find_all_medics(){
		Medic medic1 = new Medic();
		medic1.setId(1L);
		Medic medic2 = new Medic();
		medic2.setId(2L);
		
		List<Medic> medicList = new ArrayList<>();
		medicList.add(medic1);
		medicList.add(medic2);
		
		when(medicRepository.findAll()).thenReturn(medicList);

		
		List<Medic> returnedList = medicService.findAll();
		assertThat(returnedList).isNotNull();
		assertThat(returnedList.size()).isEqualTo(2);
	}
	
	@Test
	public void should_save_medic(){
		when(medicRepository.save(medic)).thenReturn(medic);
		Medic returnedMedic = medicService.save(medic);
		
		assertThat(returnedMedic).isNotNull();
		assertThat(returnedMedic.getId()).isEqualTo(1L);
		assertThat(returnedMedic.getAddress()).isEqualTo("address");
		assertThat(returnedMedic.getBirthDate()).isNotNull();
		assertThat(returnedMedic.getCurrentTitle()).isEqualTo("title");
		assertThat(returnedMedic.getEmail()).isEqualTo("email");
		assertThat(returnedMedic.getFullname()).isEqualTo("fullname");
		assertThat(returnedMedic.getGender().toString()).isEqualTo("MALE");
	}
}
