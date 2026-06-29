package com.info.academic_resource_hub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponseDTO {

    private Long id;

    private String name;

    private String subjectCode;

    private Long semesterId;

    private Integer semNumber;
}