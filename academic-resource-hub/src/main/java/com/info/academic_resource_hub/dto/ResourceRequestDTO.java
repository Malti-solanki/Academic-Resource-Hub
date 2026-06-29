package com.info.academic_resource_hub.dto;
import com.info.academic_resource_hub.enums.ResourceType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRequestDTO {

    private String title;

    private ResourceType type;

    private Long subjectId;

    private Long uploadedBy;
}