package io.pan.validation.validator;

import io.pan.validation.dto.PeriodDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<Period, PeriodDto> {

  @Override
  public boolean isValid(PeriodDto period, ConstraintValidatorContext context) {
    if (inconsistent(period)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          ConstraintMessage.PERIOD_CONSISTENCY
      ).addConstraintViolation();
      return false;
    }
    return true;
  }

  private boolean inconsistent(PeriodDto period) {
    return period.getTo().isBefore(period.getFrom());
  }
}
