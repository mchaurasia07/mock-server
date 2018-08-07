package com.mock.mockserver.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mock.mockserver.entity.UserEntity;
import java.util.Collection;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by mayank.chaurasia on 31-07-2018.
 */
public class UserPrincipal implements UserDetails {

  private Long id;

  private String fistName;

  private String lastName;

  private String username;

  @JsonIgnore
  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String fistName, String lastName, String email, String password,
                       Collection<?
                           extends GrantedAuthority> authorities) {
    this.id = id;
    this.fistName = fistName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(UserEntity userEntity) {
   /*   List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
              new SimpleGrantedAuthority(role.getName().name())
      ).collect(Collectors.toList());*//*

      */
    return new UserPrincipal(
        userEntity.getId(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getEmail(), userEntity.getPassword(),
        null
    );
  }

  public Long getId() {
    return id;
  }

  public String getFistName() {
    return fistName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
