package com.mock.mockserver.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ParametersEntity;
import com.mock.mockserver.entity.ResourceEntity;
import java.io.IOException;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class Resource {

  private Long id;
  private String url;

  @Enumerated(EnumType.STRING)
  private RequestMethod method;
  private JsonNode requestBody;
  private JsonNode responseBody;
  private String token;
  private boolean secured;
  @Enumerated(EnumType.STRING)
  private HttpStatus responseStatus;
  private boolean published;
  private List<ParametersEntity> requestParameters;

  public Resource(){

  }

  public Resource(ResourceEntity resourceEntity){
    this.id = resourceEntity.getId();
    this.url = resourceEntity.getUrl();
    this.method = resourceEntity.getMethod();
    this.token = resourceEntity.getToken();
    this.secured = resourceEntity.isSecured();
    this.responseStatus = resourceEntity.getResponseStatus();
    this.published = resourceEntity.isPublished();
    this.requestParameters = resourceEntity.getParameters();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      this.requestBody = objectMapper.readTree(resourceEntity.getRequestBody());
      this.responseBody = objectMapper.readTree(resourceEntity.getResponseBody());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public void setMethod(RequestMethod method) {
    this.method = method;
  }

  public JsonNode getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(JsonNode requestBody) {
    this.requestBody = requestBody;
  }

  public JsonNode getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(JsonNode responseBody) {
    this.responseBody = responseBody;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isSecured() {
    return secured;
  }

  public void setSecured(boolean secured) {
    this.secured = secured;
  }

  public HttpStatus getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(HttpStatus responseStatus) {
    this.responseStatus = responseStatus;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public List<ParametersEntity> getRequestParameters() {
    return requestParameters;
  }

  public void setRequestParameters(List<ParametersEntity> requestParameters) {
    this.requestParameters = requestParameters;
  }
}
