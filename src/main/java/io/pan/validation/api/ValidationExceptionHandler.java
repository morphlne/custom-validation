package io.pan.validation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler
  protected ResponseEntity<String> handle(MethodArgumentNotValidException exception, WebRequest request) {
    return new ResponseEntity<>(
        collectErrors(exception),
        HttpStatus.BAD_REQUEST
    );
  }

  private String collectErrors(BindingResult bindingResult) {
    return bindingResult.getAllErrors().stream()
        .map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining("; "));
  }
}
