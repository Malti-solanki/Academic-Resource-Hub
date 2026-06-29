package com.info.academic_resource_hub.mapper;

import com.info.academic_resource_hub.dto.BranchRequestDTO;
import com.info.academic_resource_hub.dto.BranchResponseDTO;
import com.info.academic_resource_hub.entity.Branch;

public class BranchMapper {

    public static Branch toEntity(BranchRequestDTO dto) {
        return Branch.builder()
                .name(dto.getName())
                .build();
    }

    public static BranchResponseDTO toDTO(Branch branch) {
        return BranchResponseDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .build();
    }
}