package com.mock.mockserver.model;

/**
 * Created by mayank.chaurasia on 02-08-2018.
 */
public class ApplicationResource {

  private Long applicationId;
  private Resource resource;

  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }
}
