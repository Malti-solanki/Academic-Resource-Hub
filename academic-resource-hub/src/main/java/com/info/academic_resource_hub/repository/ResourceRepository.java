package com.info.academic_resource_hub.repository;


import com.info.academic_resource_hub.entity.Resource;
import com.info.academic_resource_hub.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findBySubjectId(Long subjectId);

    List<Resource> findBySubjectIdAndType(Long subjectId, ResourceType type);

    List<Resource> findByType(ResourceType type);
}