package com.mock.mockserver.model;

import com.mock.mockserver.entity.ApplicationEntity;

/**
 * Created by mayank.chaurasia on 26-07-2018.
 */
public class Application {

  private Long id;
  private String name;
  private String context;

  public Application(){

  }

  public Application(ApplicationEntity applicationEntity){
    this.id = applicationEntity.getId();
    this.name = applicationEntity.getName();
    this.context = applicationEntity.getContext();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
