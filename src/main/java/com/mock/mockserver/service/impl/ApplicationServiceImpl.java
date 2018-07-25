package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.repository.ApplicationRepository;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.service.ApplicationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public void save(ApplicationEntity applicationEntity) {
    applicationRepository.save(applicationEntity);
  }

  @Override
  public List<ApplicationEntity> getAll() {
    return applicationRepository.findAll();
  }

  @Override
  public List<ApplicationEntity> findByUserId(Long userId) {
    Optional<UserEntity> userEntity = userRepository.findById(userId);
    return applicationRepository.findByUserEntity(userEntity.get());
  }

  @Override
  public void delete(Long applicationId) {
    Optional<ApplicationEntity> applicationEntity = applicationRepository.findById(applicationId);
    applicationRepository.delete(applicationEntity.get());
  }
}
