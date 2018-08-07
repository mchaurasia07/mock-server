package com.mock.mockserver.service.impl;

import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mayank.chaurasia on 31-07-2018.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail)
      throws UsernameNotFoundException {
    // Let people login with either username or email
    UserEntity userEntity = userRepository.findByEmail(usernameOrEmail);
        if (userEntity == null) {
         throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }


    return UserPrincipal.create(userEntity);
  }

  // This method is used by JWTAuthenticationFilter
  @Transactional
  public UserDetails loadUserById(Long id) {
    UserEntity userEntity = userRepository.findOne(id);
    if (userEntity == null){
        throw  new UsernameNotFoundException("User not found with id : " + id);
    }

    return UserPrincipal.create(userEntity);
  }
}
