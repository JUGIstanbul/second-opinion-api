package org.jugistanbul.secondopinion.api.controller;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.jugistanbul.secondopinion.api.RestHelper;
import org.jugistanbul.secondopinion.api.TestEntityHelper;
import org.jugistanbul.secondopinion.api.config.BaseIT;
import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.Medicine;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.MedicineRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.lang.Long.parseLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.jugistanbul.secondopinion.api.RestHelper.extractIdStringFromURI;

public class MedicineControllerIT extends BaseIT {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private TestEntityHelper testEntityHelper;

    private Case theCase;
    private String url = "/v1/medicine";

    @Before
    public void createTestCase() {
        theCase = testEntityHelper.createTestMedicalCase();
    }

    @Test
    public void should_save_medicine() throws Exception {
        // given

        // when
        ResponseEntity<Void> responseEntityResponseEntity = postMedicineAndGetResponse();

        //then
        URI location = responseEntityResponseEntity.getHeaders().getLocation();
        String id = extractIdStringFromURI(location);
        Medicine one = medicineRepository.findOne(Long.valueOf(id));

        assertThat(one).isNotNull();
        assertThat(location.toString()).
                isEqualTo("http://localhost:" + serverPort + "/api/v1/medicine/" + id);
        assertThat(one.getId()).isEqualTo(Long.valueOf(id));
        assertThat(responseEntityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void should_post_medicine_return_406_when_not_assigned_to_case() throws Exception {
        // given
        Medicine medicine = new Medicine();
        medicine.setName("Lorem ipsum");

        // when
        ResponseEntity<Void> responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity(url,
                        medicine,
                        Void.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_post_medicine_return_406_when_assigned_to_not_persisted_case() throws Exception {
        // given
        Medicine medicine = new Medicine();
        medicine.setName("Lorem ipsum");
        Case caseEntity = new Case();
        medicine.setRelevantCase(caseEntity);

        // when
        ResponseEntity responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity(url,
                        medicine,
                        Void.class);
        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_post_medicine_return_406_when_assigned_to_deleted_case() throws Exception {
        // given
        Medicine medicine = new Medicine();
        medicine.setName("Lorem ipsum");
        Case caseEntity = new Case();
        medicine.setRelevantCase(caseEntity);
        caseEntity.setId(32145L);

        // when
        ResponseEntity responseEntity = testRestTemplate
                .withBasicAuth("1", "1")
                .postForEntity(url,
                        medicine,
                        Void.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_delete_medicine() throws Exception {
        // given
        ResponseEntity<Void> medicine = postMedicineAndGetResponse();
        URI location = medicine.getHeaders().getLocation();
        String id = extractIdStringFromURI(location);

        // when
        ResponseEntity<Void> deleteResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/" + id,
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        Void.class);

        Medicine one = medicineRepository.findOne(Long.valueOf(id));

        // then
        assertThat(one.getModelStatus()).isEqualTo(ModelStatus.DELETED);
        assertThat(HttpStatus.NO_CONTENT).isEqualTo(deleteResponse.getStatusCode());
    }

    @Test
    public void should_delete_return_404_for_non_existing_medicine() throws Exception {
        //given

        // when
        ResponseEntity<Void> deleteResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/1234321",
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        Void.class);

        // then
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void should_put_update_medicine() throws Exception {

        // given
        ResponseEntity<Void> response = postMedicineAndGetResponse();
        URI location = response.getHeaders().getLocation();
        String id = extractIdStringFromURI(location);

        Medicine savedMedicine = medicineRepository.findOne(parseLong(id));
        String name = "New name update..";
        savedMedicine.setName(name);
        savedMedicine.setRelevantCase(theCase);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(savedMedicine),
                        Void.class);

        Medicine updatedMedicine = medicineRepository.findOne(parseLong(id));

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedMedicine.getName()).isEqualTo(name);
    }

    @Test
    public void should_put_medicine_return_406_when_not_assigned_to_case() throws Exception {
        // given
        ResponseEntity<Void> postResponse = postMedicineAndGetResponse();
        URI location = postResponse.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Medicine savedMedicine = medicineRepository.findOne(Long.parseLong(id));
        savedMedicine.setRelevantCase(null);

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(savedMedicine),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_put_medicine_return_406_when_assigned_to_not_persisted_case() throws Exception {
        // given
        ResponseEntity<Void> postResponse = postMedicineAndGetResponse();
        URI location = postResponse.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Medicine savedMedicine = medicineRepository.findOne(Long.parseLong(id));
        theCase.setId(null);
        savedMedicine.setRelevantCase(theCase);

        // when
        ResponseEntity putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(savedMedicine),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void should_put_medicine_return_406_when_assigned_to_deleted_case() throws Exception {
        // given
        ResponseEntity<Void> postResponse = postMedicineAndGetResponse();
        URI location = postResponse.getHeaders().getLocation();
        String id = RestHelper.extractIdStringFromURI(location);

        Medicine savedMedicine = medicineRepository.findOne(Long.parseLong(id));
        theCase.setId(19783L);
        savedMedicine.setRelevantCase(theCase);

        // when
        ResponseEntity putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<>(savedMedicine),
                        Void.class);

        // then
        MatcherAssert.assertThat(putResponse.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    public void should_put_return_404_for_nonexisting_medicine() throws Exception {

        // given
        ResponseEntity<Void> response = postMedicineAndGetResponse();
        URI location = response.getHeaders().getLocation();
        String id = extractIdStringFromURI(location);

        Medicine savedMedicine = medicineRepository.findOne(parseLong(id));

        // when
        ResponseEntity<Void> putResponse = testRestTemplate
                .withBasicAuth("1", "1")
                .exchange(url + "//11123",
                        HttpMethod.PUT,
                        new HttpEntity<>(savedMedicine),
                        Void.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_get_medicine() throws Exception {

        // given
        ResponseEntity<Void> createMedicineResponse = postMedicineAndGetResponse();
        Long id = RestHelper.extractIdFromURI(createMedicineResponse.getHeaders().getLocation());

        // when
        ResponseEntity<Medicine> getResult = testRestTemplate
                .withBasicAuth("1", "1")
                .getForEntity(url + "/" + id,
                        Medicine.class);

        // then
        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResult.getBody()).isNotNull();
        assertThat(getResult.getBody().getId()).isEqualTo(id);
    }

    @Test
    public void should_get_return_404_for_nonexisting_medicine() throws Exception {

        // given

        // when
        ResponseEntity<Medicine> getResult = testRestTemplate.withBasicAuth("1", "1")
                .getForEntity(url + "/545445", Medicine.class);

        // then
        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Void> postMedicineAndGetResponse() {
        Medicine medicine = new Medicine();
        medicine.setRelevantCase(theCase);
        medicine.setName("Lorem ipsum..");


        return testRestTemplate.withBasicAuth("1", "1").postForEntity(url, medicine, Void.class);
    }


}
