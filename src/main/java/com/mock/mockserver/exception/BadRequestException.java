package com.mock.mockserver.exception;

/**
 * Created by mayank.chaurasia on 02-08-2018.
 */
public class BadRequestException extends RuntimeException {

  public BadRequestException(String message){
    super(message);
  }
}
