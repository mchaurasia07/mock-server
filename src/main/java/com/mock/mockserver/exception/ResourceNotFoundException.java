package com.mock.mockserver.exception;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String message){
    super(message);
  }
}
