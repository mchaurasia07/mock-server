package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.exception.BadRequestException;
import com.mock.mockserver.exception.ForbiddenException;
import com.mock.mockserver.model.Application;
import com.mock.mockserver.model.ApplicationResource;
import com.mock.mockserver.model.UserResource;
import com.mock.mockserver.model.UserResourceResponse;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.ResourceRepository;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.security.UserPrincipal;
import com.mock.mockserver.service.ResourceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  ResourceRepository resourceRepository;
  @Autowired
  ApplicationRepository applicationRepository;
  @Autowired
  UserRepository userRepository;


  @Override
  public ResourceEntity create(ApplicationResource applicationResourceModel) {
    UserPrincipal currentUser = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (applicationResourceModel == null) {

      throw new BadRequestException("Invalid input");
    }

    if (applicationResourceModel.getApplicationId() == null ||
        applicationResourceModel.getApplicationId() <=0){
      throw new ForbiddenException("Invalid application");
    }

    ApplicationEntity applicationEntity = applicationRepository.findByIdAndUserEntity(applicationResourceModel
        .getApplicationId(), new UserEntity(currentUser));
    if (applicationEntity == null){
      throw new ForbiddenException("application not found");
    }

    ResourceEntity resourceEntity = resourceRepository.findByApplicationAndUrlAndMethod(applicationEntity,
        applicationResourceModel.getResource().getUrl(), applicationResourceModel.getResource().getMethod());
    if (resourceEntity != null) {
      throw new ForbiddenException("resource already exist");
    }
    resourceEntity = new ResourceEntity(applicationResourceModel.getResource());
    resourceEntity.setApplication(applicationEntity);

    return resourceRepository.save(resourceEntity);
  }


  public ResourceEntity update(ApplicationResource applicationResourceModel) {
    UserPrincipal currentUser = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (applicationResourceModel == null ||
        applicationResourceModel.getApplicationId() == null ||
        applicationResourceModel.getApplicationId() <=0){
      throw new ForbiddenException("Invalid application");
    }

    ApplicationEntity applicationEntity = applicationRepository.findByIdAndUserEntity(applicationResourceModel
        .getApplicationId(), new UserEntity(currentUser));
    if (applicationEntity == null){
      throw new ForbiddenException("application not found");
    }

    ResourceEntity resourceEntity = resourceRepository.findOne(applicationResourceModel.getResource().getId());
    if (resourceEntity == null) {
      throw new ForbiddenException("resource already exist");
    }

    return save(applicationResourceModel);

  }

  private ResourceEntity save(ApplicationResource applicationResourceModel) {


    ResourceEntity resourceEntity = new ResourceEntity(applicationResourceModel.getResource());
    ApplicationEntity applicationEntity = applicationRepository.findOne(applicationResourceModel.getApplicationId());
    if (applicationEntity == null){
      throw new ForbiddenException("Invalid application");
    }
    resourceEntity.setApplication(applicationEntity);

    return resourceRepository.save(resourceEntity);
  }

  @Override
  public Object findByUrl(String url) {
    ResourceEntity resourceEntity = resourceRepository.findByUrl(url);
    return resourceEntity.getResponseBody();
  }

  @Override
  public Object findByUrlAndMethod(String url, RequestMethod method) {
    ResourceEntity resourceEntity = resourceRepository.findByUrlAndMethod(url, method);
    return resourceEntity.getResponseBody();
  }

  @Override
  public List<ResourceEntity> findByApplicationId(Long applicationId) {
    ApplicationEntity applicationEntity = applicationRepository.findOne(applicationId);
    return  resourceRepository.findByApplication(applicationEntity);

  }

  @Override
  public void publishResource(UserResource userResource) {

    UserEntity userEntity = getLoggedInUser();
    ApplicationEntity applicationEntity = applicationRepository.findByIdAndUserEntity(userResource.getApplicationId(),
        userEntity);
    if (applicationEntity == null){
      throw new ForbiddenException("invalid application");
    }
    ResourceEntity resourceEntity = resourceRepository.findByIdAndApplication(userResource.getResourceId(),
        applicationEntity);
    if (resourceEntity == null){
      throw new ForbiddenException("invalid resource");
    }

    resourceEntity.setPublished(true);
    resourceRepository.save(resourceEntity);

  }

  @Override
  public List<UserResourceResponse> getResources() {
    List<UserResourceResponse> userResourceResponses = new ArrayList<>();
    UserEntity userEntity = userRepository.findOne(getLoggedInUser().getId());
    List<ApplicationEntity> applicationEntities = applicationRepository.findByUserEntity(userEntity);
    for (ApplicationEntity applicationEntity: applicationEntities){
      UserResourceResponse userResourceResponse = new UserResourceResponse();
      userResourceResponse.setApplication(new Application(applicationEntity));
      userResourceResponse.setResourcesByEntity(resourceRepository.findByApplication(applicationEntity));
      userResourceResponses.add(userResourceResponse);
    }

  return userResourceResponses;
  }

  private UserEntity getLoggedInUser(){
    return new UserEntity((UserPrincipal) (SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
  }
}
