package com.info.academic_resource_hub.dto;

import lombok.Data;

@Data
public class SemesterResponseDTO {

    private Long id;
    private Integer semNumber;
    private Long branchId;
    private String branchName;

}
