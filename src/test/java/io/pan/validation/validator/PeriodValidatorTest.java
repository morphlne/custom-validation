package io.pan.validation.validator;

import io.pan.validation.dto.PeriodDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Import(LocalValidatorFactoryBean.class)
public class PeriodValidatorTest {

  private final Validator validator;

  @Autowired
  public PeriodValidatorTest(Validator validator) {
    this.validator = validator;
  }

  @Test
  public void whenEndBeforeBeginning_thenError() {
    final PeriodDto period = new PeriodDto(
        LocalDate.of(2021, 1, 1),
        LocalDate.of(2020, 2, 1)
    );

    final Errors errors = new BeanPropertyBindingResult(period, "period");
    validator.validate(period, errors);

    assertAll(
        () -> assertEquals(1, errors.getAllErrors().size()),
        () -> assertEquals(ConstraintMessage.PERIOD_CONSISTENCY, errors.getAllErrors().get(0).getDefaultMessage())
    );
  }
}
