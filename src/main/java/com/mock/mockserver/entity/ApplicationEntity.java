package com.mock.mockserver.entity;

import com.mock.mockserver.model.Application;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
@Entity
@Table(name = "application")
public class ApplicationEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String context;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  public ApplicationEntity(){

  }

  public ApplicationEntity(Application model){
    this.id = model.getId();
    this.name = model.getName();
    this.context = model.getContext();
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

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }
}
