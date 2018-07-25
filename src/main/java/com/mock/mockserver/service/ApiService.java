package com.mock.mockserver.service;

import com.mock.mockserver.entity.ApplicationEntity;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
public interface ApiService {

  ResponseEntity processRequest(String uri,RequestMethod requestMethod, Map<String, Object> body);


}
