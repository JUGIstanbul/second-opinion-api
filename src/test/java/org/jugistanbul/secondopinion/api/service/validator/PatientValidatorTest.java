package org.jugistanbul.secondopinion.api.service.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.jugistanbul.secondopinion.api.config.BaseMockitoTest;
import org.jugistanbul.secondopinion.api.dto.PatientInformation;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.types.Gender;
import org.junit.Test;
import org.mockito.InjectMocks;


public class PatientValidatorTest extends BaseMockitoTest {

    @InjectMocks
    PatientValidator patientValidator;

    @Test
    public void should_throw_exception_when_request_is_null() {
        //given
        PatientInformation patientInformation= null;

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientInformation));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable).hasMessage("request.missing");
    }

    @Test
    public void should_throw_exception_when_email_is_null() {
        //given
        PatientInformation patientInformation = new PatientInformation();

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientInformation));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable).hasMessage("email.required");
    }

    @Test
    public void should_validate_request() {
        //given
        PatientInformation patientInformation = new PatientInformation();
        patientInformation.setEmail("test@gmail.com");
        patientInformation.setGender(Gender.MALE);

        //when
        Throwable throwable = catchThrowable(() -> patientValidator.validate(patientInformation));

        //then
        assertThat(throwable).isNull();
    }
}