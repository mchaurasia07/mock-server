package com.mock.mockserver.controller;


import com.mock.mockserver.service.EndPointService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */

@RestController
@RequestMapping("/**")
public class EndPointController {

  @Autowired
  EndPointService endPointService;


  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getResource(HttpServletRequest request){
    return endPointService.processGetRequest(request);
  }


  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity postResource(HttpServletRequest request, @RequestBody Map<String, Object> body){
    return endPointService.processPostRequest(request, body);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity putResource(HttpServletRequest request, @RequestBody Map<String, Object> body){
    return endPointService.processPutRequest(request.getRequestURI(), RequestMethod.PUT, body);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity deleteResource(HttpServletRequest request, @RequestBody Map<String, Object> body){
    return endPointService.processDeleteRequest(request.getRequestURI(), RequestMethod.DELETE, body);
  }

}
