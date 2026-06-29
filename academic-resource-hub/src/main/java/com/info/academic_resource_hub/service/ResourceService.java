package com.info.academic_resource_hub.service;


import com.info.academic_resource_hub.dto.ResourceRequestDTO;
import com.info.academic_resource_hub.dto.ResourceResponseDTO;
import com.info.academic_resource_hub.enums.ResourceType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {

    ResourceResponseDTO uploadResource(ResourceRequestDTO request, MultipartFile file);

    List<ResourceResponseDTO> getAllResources();

    ResourceResponseDTO getResourceById(Long id);

    List<ResourceResponseDTO> getResourcesBySubject(Long subjectId);

    byte[] downloadResource(Long id);

    ResourceResponseDTO deleteResource(Long id);

    public List<ResourceResponseDTO> getResourcesBySubjectAndType(Long subjectId, ResourceType type);

    ResourceResponseDTO updateResource(Long id, String title, ResourceType type, MultipartFile file);

    public List<ResourceResponseDTO> getResourcesByType(ResourceType type);
}