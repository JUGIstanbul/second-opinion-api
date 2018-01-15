package org.jugistanbul.secondopinion.api.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.entity.PastChronicDisease;
import org.jugistanbul.secondopinion.api.entity.PastMedicine;
import org.jugistanbul.secondopinion.api.entity.PastOperation;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.types.Gender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void should_create_patient() throws Exception {
        //Given
        PatientInformation request = this.createSamplePatientInformation();

        //When
        ResponseEntity<PatientResponse> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity("/v1/patients",
                        request,
                        PatientResponse.class);

        PatientResponse patientResponse = responseEntity.getBody();

        //Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(patientResponse).isNotNull();
        assertThat(patientResponse.getPatientId()).isNotNull();
    }

    @Test
    public void should_get_patient_account_info() throws Exception {

        //Given
        PatientInformation request = this.createSamplePatientInformation();

        //When
        ResponseEntity<PatientResponse> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity("/v1/patients", request, PatientResponse.class);

        PatientResponse patientResponse = responseEntity.getBody();
        Long patiendId = patientResponse.getPatientId();

        ResponseEntity<PatientInformation> patientInformationEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/patients/" + patiendId,
                        PatientInformation.class);

        PatientInformation patientInformation = patientInformationEntity.getBody();


        //Then
        assertThat(patientInformationEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(patientInformation).isNotNull();
        assertThat("user@gmail.com").isEqualTo(patientInformation.getEmail());
    }

    @Test
    public void should_fail_get_patient_account_info() throws Exception {
        //Given
        Long nonExistingPatientId = Long.valueOf(1);

        //When
        ResponseEntity<PatientInformation> patientInformationEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/patients/" + nonExistingPatientId,
                        PatientInformation.class);

        //Then
        assertThat(patientInformationEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_update_patient_account_info() {
        //Given
        Patient patient = new Patient();
        patient.setPhone("5554443322");
        patient.setEmail("adere@yahoo.com");
        patient.setUsername("adere");
        patient.setPassword("Aa123456");

        Patient patientSaved = patientRepository.save(patient);

        Long userId = patientSaved.getId();

        PatientInformation request = new PatientInformation();
        request.setPhone("05309541111");
        request.setGender(Gender.MALE);
        request.setAddress("Istanbul");
        request.setJob("Mühendis");
        request.setAddictiveDrugProfile("drug");
        request.setAlcoholConsumptionProfile("Haftada bir");
        request.setSmokerProfile("Günde yarım paket");

        PastChronicDisease pastChronicDisease = new PastChronicDisease();
        pastChronicDisease.setYear(2014);
        pastChronicDisease.setDiagnosis("Kalp");
        PastChronicDisease pastChronicDisease2 = new PastChronicDisease();
        pastChronicDisease2.setYear(2013);
        pastChronicDisease2.setDiagnosis("Seker");
        Set<PastChronicDisease> pastChronicDiseaseSet = new HashSet<>(Arrays.asList(pastChronicDisease, pastChronicDisease2));

        PastMedicine pastMedicine = new PastMedicine();
        pastMedicine.setMedicineName("Aspirin");
        pastMedicine.setYearStarted(2012);
        pastMedicine.setYearEnded(2014);
        Set<PastMedicine> pastMedicineList = new HashSet<>(Arrays.asList(pastMedicine));

        PastOperation pastOperation = new PastOperation();
        pastOperation.setOperationName("Kil donmesi");
        pastOperation.setYear(2009);
        Set<PastOperation> pastOperationList = new HashSet<>(Arrays.asList(pastOperation));

        request.setChronicDiseases(pastChronicDiseaseSet);
        request.setMedications(pastMedicineList);
        request.setPastOperations(pastOperationList);

        //When
        HttpEntity<PatientInformation> requestEntity = new HttpEntity<PatientInformation>(request);

        ResponseEntity<PatientInformation> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange("/v1/patients/" + userId,
                        HttpMethod.PUT,
                        requestEntity,
                        PatientInformation.class,
                        new HashMap<String, String>());

        ResponseEntity<PatientInformation> getResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity("/v1/patients/{userId}",
                        PatientInformation.class,
                        userId);


        //Then
        assertThat(putResponse).isNotNull();
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        PatientInformation patientInfo = getResponse.getBody();
        assertThat(getResponse).isNotNull();
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(patientInfo.getGender()).isEqualTo(Gender.MALE);
        assertThat(patientInfo.getAddress()).isEqualTo("Istanbul");
        assertThat(patientInfo.getPhone()).isEqualTo("05309541111");
        assertThat(patientInfo.getJob()).isEqualTo("Mühendis");
        assertThat(patientInfo.getAddictiveDrugProfile()).isEqualTo("drug");
        assertThat(patientInfo.getAlcoholConsumptionProfile()).isEqualTo("Haftada bir");
        assertThat(patientInfo.getSmokerProfile()).isEqualTo("Günde yarım paket");
        assertThat(patientInfo.getChronicDiseases()).extracting("diagnosis").contains(pastChronicDisease.getDiagnosis(), pastChronicDisease2.getDiagnosis());
        assertThat(patientInfo.getMedications()).extracting("medicineName").contains(pastMedicine.getMedicineName());
        assertThat(patientInfo.getPastOperations()).extracting("operationName").contains(pastOperation.getOperationName());
    }

    private PatientInformation createSamplePatientInformation() {
        PatientInformation request = new PatientInformation();
        request.setUsername("user");
        request.setPassword("test123");
        request.setEmail("user@gmail.com");
        request.setPhone("05309547630");
        request.setAddress("demo");
        request.setBirthday("01/01/1982");
        request.setJob("demo");
        request.setGender(Gender.MALE);
        request.setSmokerProfile("demoSmokerProfile");
        request.setAddictiveDrugProfile("demoDrugProfile");
        request.setAlcoholConsumptionProfile("demoConsumptionProfile");

        return request;
    }
}
