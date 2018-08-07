package com.mock.mockserver.service;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.model.Application;
import java.util.List;

/**
 * Created by mayank.chaurasia on 25-07-2018.
 */
public interface ApplicationService {

  ApplicationEntity save(Application application);
  List<ApplicationEntity> getAll();
  List<ApplicationEntity> findByUserId(Long userId);
  void delete(Long applicationId);
}
