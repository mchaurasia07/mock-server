package com.mock.mockserver.controller;


import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.service.ApplicationService;
import com.mock.mockserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  UserService userService;


  @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserEntity userEntity){
    userService.save(userEntity);
    return new ResponseEntity( HttpStatus.CREATED);
  }

  @RequestMapping( method = RequestMethod.GET)
  public ResponseEntity findByEmail(@RequestParam("email") String email){

    return new ResponseEntity(userService.findByEmail(email), HttpStatus.OK);
  }




}
