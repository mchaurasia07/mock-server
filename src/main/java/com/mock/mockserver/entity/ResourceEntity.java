package com.mock.mockserver.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mockserver.model.ApplicationResource;
import com.mock.mockserver.model.Resource;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */

@Entity
@Table(name = "resource")
public class ResourceEntity implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String url;
  @Column(columnDefinition = "LONGTEXT")

  @Enumerated(EnumType.STRING)
  private RequestMethod method;
  private String requestBody;
  private String responseBody;
  private String token;
  private boolean secured;
  @Enumerated(EnumType.STRING)
  private HttpStatus responseStatus;
  private boolean published;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "param_id")
  private List<ParametersEntity> parameters;

  @ManyToOne
  @JoinColumn(name = "application_id")
  private ApplicationEntity application;

  public ResourceEntity(){

  }

 /* public ResourceEntity(ApplicationResource applicationResource){

    this.id = applicationResource.getResource().getId();
    this.url = applicationResource.getResource().getUrl();
    this.method = applicationResource.getResource().getMethod();
    this.requestBody = applicationResource.getResource().getRequestBody();
    this.responseBody = applicationResource.getResource().getResponseBody();
    this.secured = applicationResource.getResource().isSecured();
    this.responseStatus = applicationResource.getResource().getResponseStatus();
    this.parameters = applicationResource.getResource().getParameters();
    this.published = applicationResource.getResource().isPublished();
    this.application = new ApplicationEntity(applicationResource.getApplication());
  }*/

  public ResourceEntity(Resource model){
    ObjectMapper objectMapper = new ObjectMapper();
    this.id = model.getId();
    this.url = model.getUrl();
    this.method = model.getMethod();
    this.secured = model.isSecured();
    this.responseStatus = model.getResponseStatus();
    this.parameters = model.getRequestParameters();
    this.published = model.isPublished();
    try {
      this.requestBody = objectMapper.writeValueAsString(model.getRequestBody());
      this.responseBody = objectMapper.writeValueAsString(model.getResponseBody());
    } catch (JsonProcessingException e) {
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

  public List<ParametersEntity> getParameters() {
    return parameters;
  }

  public void setParameters(List<ParametersEntity> parameters) {
    this.parameters = parameters;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public void setMethod(RequestMethod method) {
    this.method = method;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  public ApplicationEntity getApplication() {
    return application;
  }

  public void setApplication(ApplicationEntity application) {
    this.application = application;
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
}
