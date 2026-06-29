package com.info.academic_resource_hub.service;

import com.info.academic_resource_hub.dto.BranchRequestDTO;
import com.info.academic_resource_hub.dto.BranchResponseDTO;
import com.info.academic_resource_hub.entity.Branch;

import java.util.List;

public interface BranchService {

    BranchResponseDTO createBranch(BranchRequestDTO request);

    List<BranchResponseDTO> getAllBranches();

    BranchResponseDTO getBranchById(Long id);

    BranchResponseDTO updateBranch(Long id, BranchRequestDTO request);

    BranchResponseDTO deleteBranch(Long id);
}