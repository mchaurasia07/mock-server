package com.mock.mockserver.service;

import com.mock.mockserver.entity.ApplicationEntity;
import java.util.List;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
public interface ApplicationService {

  void save(ApplicationEntity applicationEntity);
  List<ApplicationEntity> getAll();
  List<ApplicationEntity> findByUserId(Long userId);
  void delete(Long applicationId);
}
