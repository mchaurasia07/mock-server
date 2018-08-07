package com.mock.mockserver.exception;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class ForbiddenException extends RuntimeException{

  public ForbiddenException(String message){
    super(message);
  }
}
