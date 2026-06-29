package com.info.academic_resource_hub.dto;

import com.info.academic_resource_hub.enums.ResourceType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceResponseDTO {

    private Long id;

    private String title;

    private String fileName;

    private Long fileSize;

    private ResourceType type;

    private LocalDateTime uploadedAt;

    private Long subjectId;

    private String subjectName;

    private Long uploadedBy;

}