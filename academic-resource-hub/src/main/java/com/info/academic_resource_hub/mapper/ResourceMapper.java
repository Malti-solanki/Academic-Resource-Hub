package com.info.academic_resource_hub.mapper;

import com.info.academic_resource_hub.dto.ResourceResponseDTO;
import com.info.academic_resource_hub.entity.Resource;

public class ResourceMapper {

    public static ResourceResponseDTO toDTO(Resource resource) {

        return ResourceResponseDTO.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .fileName(resource.getFileName())
                .fileSize(resource.getFileSize())
                .type(resource.getType())
                .uploadedAt(resource.getUploadedAt())
                .subjectId(resource.getSubject().getId())
                .subjectName(resource.getSubject().getName())
                .uploadedBy(resource.getUploadedBy().getId())
                .build();
    }
}
