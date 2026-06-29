package com.info.academic_resource_hub.serviceImpl;

import com.info.academic_resource_hub.dto.BranchRequestDTO;
import com.info.academic_resource_hub.dto.BranchResponseDTO;
import com.info.academic_resource_hub.entity.Branch;
import com.info.academic_resource_hub.exception.DuplicateResourceException;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.mapper.BranchMapper;
import com.info.academic_resource_hub.repository.BranchRepository;
import com.info.academic_resource_hub.service.BranchService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public BranchResponseDTO createBranch(BranchRequestDTO request) {

        if (branchRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Branch already exists with name: " + request.getName());
        }

        Branch branch = BranchMapper.toEntity(request);
        Branch savedBranch = branchRepository.save(branch);

        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public List<BranchResponseDTO> getAllBranches() {

        return branchRepository.findAll()
                .stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchResponseDTO getBranchById(Long id) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        return BranchMapper.toDTO(branch);
    }

    @Override
    public BranchResponseDTO updateBranch(Long id, BranchRequestDTO request) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        if (!branch.getName().equalsIgnoreCase(request.getName())
                && branchRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Branch already exists with name: " + request.getName());
        }

        branch.setName(request.getName());
        Branch updatedBranch = branchRepository.save(branch);

        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public BranchResponseDTO deleteBranch(Long id) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        branchRepository.delete(branch);

        return BranchMapper.toDTO(branch);
    }
}