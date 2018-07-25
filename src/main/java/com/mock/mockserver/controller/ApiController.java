package com.mock.mockserver.controller;


import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.service.ApiService;
import com.mock.mockserver.service.ResourceService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
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
@RequestMapping("/**")
public class ApiController {

  @Autowired
  ApiService apiService;


  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getResource(HttpServletRequest request){
    return apiService.processRequest(request.getRequestURI(), RequestMethod.GET, null);
  }


  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity postResource(HttpServletRequest request, @RequestBody Map<String, Object> body){
    return apiService.processRequest(request.getRequestURI(), RequestMethod.POST, body);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity putResource(HttpServletRequest request, @RequestBody Map<String, Object> body){
    return apiService.processRequest(request.getRequestURI(), RequestMethod.PUT, body);
  }

}
