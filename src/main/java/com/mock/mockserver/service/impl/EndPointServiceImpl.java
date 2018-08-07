package com.mock.mockserver.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ParametersEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.exception.BadRequestException;
import com.mock.mockserver.exception.ResourceNotFoundException;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.ResourceRepository;
import com.mock.mockserver.service.EndPointService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
@Service
public class EndPointServiceImpl implements EndPointService {

  @Autowired
  ApplicationRepository applicationRepository;
  @Autowired
  ResourceRepository resourceRepository;
  @Autowired
  ObjectMapper objectMapper;

  @Override
  public ResponseEntity processPostRequest(HttpServletRequest request, Map<String, Object> requestBody) {
    String resourceUrl = getUri(request);
    validateContext(resourceUrl);
    ResourceEntity resourceEntity = getResourceByUriAndMethod(resourceUrl, RequestMethod.POST);
    validateRequestBody(requestBody, resourceEntity);
    return createResponseEntity(resourceEntity);
  }

  @Override
  public ResponseEntity processGetRequest(HttpServletRequest request) {
    String resourceUrl = getUri(request);
    validateContext(resourceUrl);
    ResourceEntity resourceEntity = getResourceByUriAndMethod(resourceUrl, RequestMethod.GET);
    if (resourceEntity.getParameters() != null && resourceEntity.getParameters().size() > 0) {
      validateRequestParam(request, resourceEntity.getParameters());
    }
    return createResponseEntity(resourceEntity);

  }

  @Override
  public ResponseEntity processPutRequest(String uri, RequestMethod requestMethod, Map<String, Object> body) {
    return null;
  }

  @Override
  public ResponseEntity processDeleteRequest(String uri, RequestMethod requestMethod, Map<String, Object> body) {
    return null;
  }

  private void validateRequestBody(Map<String, Object> requestBody, ResourceEntity resourceEntity) {

    Map<String, Object> map;
    try {
      map = objectMapper.readValue(resourceEntity.getRequestBody(), Map.class);
    } catch (IOException e) {
      throw new RuntimeException("application error");
    }
    if (requestBody == null || !requestBody.equals(map)) {
      throw new BadRequestException("Bad Request");
    }
  }

  private String getUri(HttpServletRequest request) {
    return request.getRequestURI();
  }

  private void validateRequestParam(HttpServletRequest request, List<ParametersEntity> parametersEntities) {
    Map<String, String[]> paramMap = request.getParameterMap();
    for (ParametersEntity parametersEntity : parametersEntities) {
      if (parametersEntity.isRequired()) {
        if (paramMap.get(parametersEntity.getName()) == null) {
          throw new BadRequestException("missing mandatory Parameters");
        }
      }
    }
  }

  private void validateContext(String uri) {
    String context = uri.split("/")[1];
    ApplicationEntity applicationEntity = applicationRepository.findByContext(context);
    if (applicationEntity == null) {
      throw new ResourceNotFoundException("invalid context");
    }
  }

  private ResourceEntity getResourceByUriAndMethod(String uri, RequestMethod method) {
    uri = uri.substring(uri.indexOf("/", 2));
    ResourceEntity resourceEntity = resourceRepository.findByUrlAndMethodAndPublished(uri, method, true);
    if (resourceEntity == null) {
      throw new ResourceNotFoundException("not found");
    }
    return resourceEntity;
  }

  private ResponseEntity createResponseEntity(ResourceEntity resourceEntity) {
    try {
      JsonNode jsonNode = objectMapper.readTree(resourceEntity.getResponseBody());
      return new ResponseEntity(jsonNode, resourceEntity.getResponseStatus());
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

}
