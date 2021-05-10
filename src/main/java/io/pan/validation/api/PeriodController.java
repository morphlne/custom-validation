package io.pan.validation.api;

import io.pan.validation.dto.PeriodDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PeriodController {

  @PostMapping("/period")
  public String period(@RequestBody @Valid PeriodDto period) {
    return "Period is ok";
  }
}
