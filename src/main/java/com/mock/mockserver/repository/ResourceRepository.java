package com.mock.mockserver.repository;

import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.entity.ResourceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mayank.chaurasia on 04-06-2018.
 */

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

  ResourceEntity findByUrl(String url);
  ResourceEntity findByUrlAndMethod(String url, RequestMethod method);
  ResourceEntity findByUrlAndMethodAndPublished(String url, RequestMethod method, boolean published);
  ResourceEntity findByIdAndApplication(Long id, ApplicationEntity applicationEntity);
  List<ResourceEntity> findByApplication(ApplicationEntity application);
  ResourceEntity findByApplicationAndUrlAndMethod(ApplicationEntity applicationEntity, String url, RequestMethod
      method);

}
