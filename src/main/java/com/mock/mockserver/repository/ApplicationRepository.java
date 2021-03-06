package com.mock.mockserver.repository;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.UserEntity;
import java.util.List;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
  ApplicationEntity findByContext(String context);
  ApplicationEntity findByUserEntityAndContext(UserEntity userEntity, String context);
  List<ApplicationEntity> findByUserEntity(UserEntity userEntity);
  ApplicationEntity findByIdAndUserEntity(Long id, UserEntity userEntity);
}
