package com.mock.mockserver.controller;

import com.mock.mockserver.entity.UserEntity;
import com.mock.mockserver.model.ApiResponse;
import com.mock.mockserver.model.JwtAuthenticationResponse;
import com.mock.mockserver.model.LoginRequest;
import com.mock.mockserver.model.SignUpRequest;
import com.mock.mockserver.repository.UserRepository;
import com.mock.mockserver.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mayank.chaurasia on 31-07-2018.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;


  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
    UserEntity userEntity = userRepository.findByEmail(signUpRequest.getEmail());
    if (userEntity != null){
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }


    // Creating user's account
    userEntity = new UserEntity();
    userEntity.setEmail(signUpRequest.getEmail());
    userEntity.setFirstName(signUpRequest.getFirstName());
    userEntity.setLastName(signUpRequest.getLastName());

    userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

   /* Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
        .orElseThrow(() -> new AppException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));*/

    UserEntity result = userRepository.save(userEntity);

   /* URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{username}")
        .buildAndExpand(result.getUsername()).toUri();
*/
    return new ResponseEntity(result, HttpStatus.CREATED);
  }
}
