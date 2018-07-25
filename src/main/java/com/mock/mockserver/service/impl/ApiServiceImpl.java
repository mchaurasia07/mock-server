package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.ResourceRepository;
import com.mock.mockserver.service.ApiService;
import java.util.Map;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
@Service
public class ApiServiceImpl implements ApiService {

  @Autowired
  ApplicationRepository applicationRepository;
  @Autowired
  ResourceRepository resourceRepository;

  @Override
  public ResponseEntity processRequest(String uri, RequestMethod requestMethod, Map<String, Object> body) {
    String resourceUrl = uri;
    String context = uri.split("/")[0];

    ApplicationEntity applicationEntity = applicationRepository.findByContext(context);
    if (applicationEntity != null){
     resourceUrl = uri.substring(uri.indexOf("/", 2));
    }

    ResourceEntity resourceEntity = resourceRepository.findByUrlAndMethod(resourceUrl, requestMethod);
    return new ResponseEntity(resourceEntity.getResponseBody(), resourceEntity.getResponseStatus());
  }

}
