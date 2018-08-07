package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.exception.ForbiddenException;
import com.mock.mockserver.model.Application;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.security.UserPrincipal;
import com.mock.mockserver.service.ApplicationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

  @Autowired
  ApplicationRepository applicationRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public ApplicationEntity save(Application application) {

    if (StringUtils.isEmpty(application.getContext())){
      throw new ForbiddenException("missing mandatory parameter");
    }

    // getting current user entity
    UserEntity userEntity = getLoggedInUesr();

    // validate if application for user exist
    ApplicationEntity applicationEntity = applicationRepository.findByUserEntityAndContext(userEntity, application.getContext());
    if (applicationEntity != null){
      throw  new ForbiddenException(application.getContext() + " context is already defined for application " +
          applicationEntity.getName());
    }

    //create application
    applicationEntity = new ApplicationEntity(application);
    applicationEntity.setUserEntity(userEntity);
    return applicationRepository.save(applicationEntity);
  }

  @Override
  public List<ApplicationEntity> getAll() {
    return applicationRepository.findAll();
  }

  @Override
  public List<ApplicationEntity> findByUserId(Long userId) {
    UserEntity userEntity = userRepository.findOne(userId);
    return applicationRepository.findByUserEntity(userEntity);
  }

  @Override
  public void delete(Long applicationId) {
    ApplicationEntity applicationEntity = applicationRepository.findOne(applicationId);
    applicationRepository.delete(applicationEntity);
  }

  private UserEntity getLoggedInUesr(){
    return new UserEntity((UserPrincipal) (SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
  }
}
