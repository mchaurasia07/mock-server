package com.mock.mockserver.model;

import com.mock.mockserver.entity.ResourceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class UserResourceResponse implements Serializable {

  Application application;
  List<Resource> resources = new ArrayList<>();

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  public void setResourcesByEntity(List<ResourceEntity> resourceEntities) {
    for (ResourceEntity resourceEntity: resourceEntities){
      resources.add(new Resource(resourceEntity));
    }

  }
}
