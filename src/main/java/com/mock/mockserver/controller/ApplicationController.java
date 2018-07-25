package com.mock.mockserver.controller;


import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.service.ApplicationService;
import com.mock.mockserver.service.ResourceService;
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
@RequestMapping("application")
public class ApplicationController {

  @Autowired
  ApplicationService applicationService;


  @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ApplicationEntity applicationEntity){
    applicationService.save(applicationEntity);
    return new ResponseEntity( HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity findByUserId(@RequestParam("userId") Long userId){
    return new ResponseEntity(applicationService.findByUserId(userId), HttpStatus.OK);
  }

  @RequestMapping(value="all", method = RequestMethod.GET)
  public ResponseEntity getAll(){
    return new ResponseEntity(applicationService.getAll(), HttpStatus.OK);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable("id") Long applicationId){
    applicationService.delete(applicationId);
    return new ResponseEntity( HttpStatus.OK);
  }


}
