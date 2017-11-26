package org.jugistanbul.secondopinion.api.validator;

import org.jugistanbul.secondopinion.api.config.BaseMockitoTest;
import org.jugistanbul.secondopinion.api.entity.Medic;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicValidatorTest extends BaseMockitoTest{

	@Test
	public void ShouldHasNoValidationError(){
		MedicValidator medicValidator = new MedicValidator();
		
		Medic medic = new Medic();
		medic.setUsername("username");
		medic.setEmail("email");
		medic.setPassword("password");
		
		Errors errors = new BeanPropertyBindingResult(medic, "medic");
		medicValidator.validate(medic, errors);
		
		assertThat(errors.hasErrors()).isFalse();
	}
	
	@Test
	public void ShouldHasUsernameValidationError(){
		MedicValidator medicValidator = new MedicValidator();
		
		Medic medic = new Medic();
		medic.setEmail("email");
		medic.setPassword("password");
		
		Errors errors = new BeanPropertyBindingResult(medic, "medic");
		medicValidator.validate(medic, errors);
		
		assertThat(errors.hasErrors()).isTrue();
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("username")).isNotNull();
	}
	
	@Test
	public void ShouldHasEmailValidationError(){
		MedicValidator medicValidator = new MedicValidator();
		
		Medic medic = new Medic();
		medic.setUsername("username");
		medic.setPassword("password");
		
		Errors errors = new BeanPropertyBindingResult(medic, "medic");
		medicValidator.validate(medic, errors);
		
		assertThat(errors.hasErrors()).isTrue();
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("email")).isNotNull();
	}
	
	@Test
	public void ShouldHasPasswordValidationError(){
		MedicValidator medicValidator = new MedicValidator();
		
		Medic medic = new Medic();
		medic.setUsername("username");
		medic.setEmail("email");
		
		Errors errors = new BeanPropertyBindingResult(medic, "medic");
		medicValidator.validate(medic, errors);
		
		assertThat(errors.hasErrors()).isTrue();
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("password")).isNotNull();
	}
}
