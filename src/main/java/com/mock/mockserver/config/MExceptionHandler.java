package com.mock.mockserver.config;

import com.mock.mockserver.exception.BadRequestException;
import com.mock.mockserver.exception.ForbiddenException;
import com.mock.mockserver.exception.ResourceNotFoundException;
import com.mock.mockserver.model.ErrorDetails;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by mayank.chaurasia on 06-08-2018.
 * */

@EnableWebMvc
 @ControllerAdvice
 public class MExceptionHandler {

 @ExceptionHandler(ResourceNotFoundException.class)
 public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
 request.getDescription(false));
 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(BadRequestException.class)
 public final ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
      request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(ForbiddenException.class)
 public final ResponseEntity<ErrorDetails> handleForbiddenException(ForbiddenException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
      request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
 }
}
