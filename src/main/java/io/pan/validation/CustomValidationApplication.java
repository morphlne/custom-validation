package io.pan.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomValidationApplication {

  public static void main(String[] args) {
    new SpringApplication(CustomValidationApplication.class).run(args);
  }
}
