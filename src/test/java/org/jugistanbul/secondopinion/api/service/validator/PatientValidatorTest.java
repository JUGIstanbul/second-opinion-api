package org.jugistanbul.secondopinion.api.service.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.jugistanbul.secondopinion.api.dto.PatientRequest;
import org.jugistanbul.secondopinion.api.exception.PatientValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PatientValidatorTest {

    @InjectMocks
    PatientValidator patientValidator;

    @Test
    public void should_throw_exception_when_request_is_null() {
        //given
        PatientRequest patientRequest = null;

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(PatientValidationException.class);
        assertThat(throwable).hasMessage("request.missing");
    }

    @Test
    public void should_throw_exception_when_email_is_null() {
        //given
        PatientRequest patientRequest = new PatientRequest();

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(PatientValidationException.class);
        assertThat(throwable).hasMessage("email.required");
    }

    @Test
    public void should_validate_request() {
        //given
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setEmail("test@gmail.com");

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientRequest));

        //then
        assertThat(throwable).isNull();
    }
}