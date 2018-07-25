package com.mock.mockserver.service;

import com.mock.mockserver.entity.UserEntity;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
public interface UserService {

  void save(UserEntity userEntity);

  UserEntity findByEmail(String email);

}
