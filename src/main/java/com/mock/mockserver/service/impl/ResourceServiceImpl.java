package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.ResourceRepository;
import com.mock.mockserver.service.ResourceService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public void createResource(ResourceEntity resourceEntity) {
    resourceRepository.save(resourceEntity);
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
    Optional<ApplicationEntity> applicationEntity = applicationRepository.findById(applicationId);
    return  resourceRepository.findByApplication(applicationEntity.get());

  }
}
