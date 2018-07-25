package com.mock.mockserver.controller;


import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.service.ResourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */

@RestController
@RequestMapping("resource")
public class ResourceController {

  @Autowired
  ResourceService resourceService;


  @RequestMapping( method = RequestMethod.POST)
  public ResponseEntity create(@RequestBody ResourceEntity resourceEntity){
    resourceService.createResource(resourceEntity);
    return new ResponseEntity( HttpStatus.CREATED);
  }

  @RequestMapping( value="{applicationId}", method = RequestMethod.GET)
  public ResponseEntity getByApplicationId(@PathVariable("applicationId") Long applicationId){
    return new ResponseEntity(resourceService.findByApplicationId(applicationId), HttpStatus.OK);
  }



}
