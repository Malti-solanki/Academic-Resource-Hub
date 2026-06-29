package com.info.academic_resource_hub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchRequestDTO {

    @NotBlank(message = "Branch name is required")
    private String name;
}