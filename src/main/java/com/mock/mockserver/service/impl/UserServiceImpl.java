package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
@Service
public class UserServiceImpl implements UserService {

@Autowired
  UserRepository userRepository;

  @Override
  public void save(UserEntity userEntity) {
    userRepository.save(userEntity);
  }

  @Override
  public UserEntity findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
