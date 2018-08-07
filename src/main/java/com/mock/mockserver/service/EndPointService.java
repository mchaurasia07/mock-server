package com.mock.mockserver.service;

import com.mock.mockserver.entity.ApplicationEntity;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
public interface EndPointService {

  ResponseEntity processPostRequest(HttpServletRequest request, Map<String, Object> body);
  public ResponseEntity processGetRequest(HttpServletRequest request);
  public ResponseEntity processPutRequest(String uri, RequestMethod requestMethod, Map<String, Object> body);
  public ResponseEntity processDeleteRequest(String uri, RequestMethod requestMethod, Map<String, Object> body);


}
