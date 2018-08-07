package com.mock.mockserver.model;

import java.io.Serializable;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class UserResource implements Serializable {

  private long resourceId;
  private long userId;
  private long applicationId;

  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }
}
