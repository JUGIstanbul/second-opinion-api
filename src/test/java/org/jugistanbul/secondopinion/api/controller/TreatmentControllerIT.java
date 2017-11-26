package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.TreatmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;

public class TreatmentControllerIT extends BaseIT {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private CaseRepository caseRepository;

    private Case theCase;

    @Before
    public void createTestMedicalCase() {
        theCase = new Case();
        theCase.setIllnessStartDate(LocalDate.MAX);
        theCase.setNickname("R2-D2");
        theCase.setNote("Lorem ipsum dolor sit amet");

        theCase = caseRepository.save(theCase);
    }

    @Test
    public void should_save_treatment() throws Exception {

        // given
        // when
        ResponseEntity<Void> responseEntityResponseEntity = getCreateTreatmentResponse();

        //then
        URI location = responseEntityResponseEntity.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);
        Treatment one = treatmentRepository.findOne(Long.valueOf(id));
        assertNotNull(one);
        assertThat(location.toString(), equalTo("http://localhost:" + serverPort + "/api/v1/treatments/" + id));
        assertThat(responseEntityResponseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void should_delete_treatment() throws Exception {

        // given
        ResponseEntity<Void> treatment = getCreateTreatmentResponse();
        URI location = treatment.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        // when
        ResponseEntity<Void> deleteResponse = testRestTemplate.withBasicAuth("1", "1").exchange("/v1/treatments/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        Treatment one = treatmentRepository.findOne(Long.valueOf(id));

        // then
        assertThat(one.getModelStatus(), equalTo(ModelStatus.DELETED));
        assertThat(HttpStatus.NO_CONTENT, equalTo(deleteResponse.getStatusCode()));

    }

    @Test
    public void should_delete_return_404_for_non_existing_treatment() throws Exception {

        //given

        // when
        ResponseEntity<Void> deleteResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/131242112", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        // then
        assertThat(deleteResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

    }

    @Test
    public void should_post_return_406_for_invalid_case() throws Exception {

        // given
        Treatment treatmentEntity = new Treatment();
        treatmentEntity.setDescription("Lorem ipsum");

        // when
        ResponseEntity<Void> responseEntity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/treatments", treatmentEntity, Void.class);

        // then
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));

        // given
        Case caseEntity = new Case();
        treatmentEntity.setRelevantCase(caseEntity);

        // when
        responseEntity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/treatments", treatmentEntity, Void.class);

        // then
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));

        // given
        caseEntity.setId(121132L);

        // when
        responseEntity = testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/treatments", treatmentEntity, Void.class);

        // then
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));

    }

    @Test
    public void should_put_update_treatment() throws Exception {

        // given
        ResponseEntity<Void> response = getCreateTreatmentResponse();
        URI location = response.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Treatment savedTreatment = treatmentRepository.findOne(Long.parseLong(id));
        savedTreatment.setDescription("New Description, Updated");
        savedTreatment.setRelevantCase(theCase);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/" + id, HttpMethod.PUT, new HttpEntity<>(savedTreatment), Void.class);

        // then
        assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.OK));

        // when
        savedTreatment = treatmentRepository.findOne(Long.parseLong(id));

        // then
        assertThat(savedTreatment.getDescription(), equalTo("New Description, Updated"));
    }

    @Test
    public void should_put_return_406_for_invalid_case() throws Exception {

        // given
        ResponseEntity<Void> postResponse = getCreateTreatmentResponse();
        URI location = postResponse.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Treatment savedTreatment = treatmentRepository.findOne(Long.parseLong(id));
        savedTreatment.setRelevantCase(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/" + id, HttpMethod.PUT, new HttpEntity<>(savedTreatment), Void.class);

        // then
        assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));


        // given
        theCase.setId(104243L);
        savedTreatment.setRelevantCase(theCase);

        // when
        putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/" + id, HttpMethod.PUT, new HttpEntity<>(savedTreatment), Void.class);

        // then
        assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));

        // given
        theCase.setId(null);

        // when
        putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/" + id, HttpMethod.PUT, new HttpEntity<>(savedTreatment), Void.class);

        // then
        assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    public void should_put_return_404_for_nonexisting_treatment() throws Exception {

        // given
        ResponseEntity<Void> response = getCreateTreatmentResponse();
        URI location = response.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Treatment savedTreatment = treatmentRepository.findOne(Long.parseLong(id));

        // when
        ResponseEntity<Void> putResponse = testRestTemplate.withBasicAuth("1", "1")
                .exchange("/v1/treatments/1938412", HttpMethod.PUT, new HttpEntity<>(savedTreatment), Void.class);

        // then
        assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void should_get_treatment() throws Exception {

        // given
        ResponseEntity<Void> createTreatmentResponse = getCreateTreatmentResponse();
        Long id = RestHelper.extractIdFromURI(createTreatmentResponse.getHeaders().getLocation());

        // when
        ResponseEntity<Treatment> getResult = testRestTemplate.withBasicAuth("1", "1")
                .getForEntity("/v1/treatments/" + id, Treatment.class);

        // then
        assertThat(getResult.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(getResult.getBody(), notNullValue());
        assertThat(getResult.getBody().getId(), equalTo(id));
    }

    @Test
    public void should_get_return_404_for_nonexisting_treatment() throws Exception {

        // given

        // when
        ResponseEntity<Treatment> getResult = testRestTemplate.withBasicAuth("1", "1")
                .getForEntity("/v1/treatments/23214123", Treatment.class);

        // then
        assertThat(getResult.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<Void> getCreateTreatmentResponse() {
        Treatment treatment = new Treatment();
        treatment.setRelevantCase(theCase);
        treatment.setDescription("Lorem ipsum...");

        return testRestTemplate.withBasicAuth("1", "1").postForEntity("/v1/treatments", treatment, Void.class);
    }
}
