package com.mock.mockserver.service;


import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.model.ApplicationResource;
import com.mock.mockserver.model.UserResource;
import com.mock.mockserver.model.UserResourceResponse;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */
public interface ResourceService {

  ResourceEntity create(ApplicationResource applicationResource);
  ResourceEntity update(ApplicationResource applicationResource);
  Object findByUrl(String url);
  Object findByUrlAndMethod(String url, RequestMethod method);
  List<ResourceEntity> findByApplicationId(Long applicationId);
  void publishResource(UserResource userResource);
  List<UserResourceResponse> getResources();

}
