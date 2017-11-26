package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.config.BaseMockitoTest;
import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.dto.PatientResponse;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.service.validator.PatientValidator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doThrow;

public class PatientServiceTest extends BaseMockitoTest {

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientValidator patientValidator;

    //Happy path
    @Test
    public void should_create_patient() throws Exception {
        //Given
        PatientRequest request = new PatientRequest();
        request.setUsername("eilhan");
        request.setPassword("test123");
        request.setEmail("eilhan@gmail.com");
        request.setPhone("05309547629");

        //When
        PatientResponse patientResponse = patientService.create(request);

        //Then
        assertThat(patientResponse).isNotNull();
        assertThat(patientResponse.getPatientId()).isNotNull();
    }

    @Test
    public void should_throw_exception_when_request_is_missing() throws Exception {
        //Given
        PatientRequest request = null;

        doThrow(EntityValidationException.class).when(patientValidator).validate(request);

        //When
        Throwable throwable = catchThrowable(() -> patientService.create(request));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(EntityValidationException.class);
    }
}
