package com.info.academic_resource_hub.controller;
import com.info.academic_resource_hub.dto.BranchRequestDTO;
import com.info.academic_resource_hub.dto.BranchResponseDTO;
import com.info.academic_resource_hub.service.BranchService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    // Create Branch
    @PostMapping
    public ResponseEntity<BranchResponseDTO> createBranch(
            @Valid @RequestBody BranchRequestDTO request) {

        BranchResponseDTO response = branchService.createBranch(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Get All Branches
    @GetMapping
    public ResponseEntity<List<BranchResponseDTO>> getAllBranches() {

        List<BranchResponseDTO> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }

    //get Branch By ID
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(
            @PathVariable Long id) {

        BranchResponseDTO branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    // Update Branch
    @PutMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> updateBranch(
            @PathVariable Long id,
            @Valid @RequestBody BranchRequestDTO request) {

        BranchResponseDTO updatedBranch = branchService.updateBranch(id, request);
        return ResponseEntity.ok(updatedBranch);
    }

    // Delete Branch
    @DeleteMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> deleteBranch(
            @PathVariable Long id) {

        BranchResponseDTO deletedBranch = branchService.deleteBranch(id);
        return ResponseEntity.ok(deletedBranch);
    }


}