package com.info.academic_resource_hub.controller;
import com.info.academic_resource_hub.dto.SemesterRequestDTO;
import com.info.academic_resource_hub.dto.SemesterResponseDTO;
import com.info.academic_resource_hub.service.SemesterService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    // CREATE SEMESTER
    @PostMapping
    public ResponseEntity<SemesterResponseDTO> createSemester(
            @RequestBody SemesterRequestDTO request) {

        SemesterResponseDTO response = semesterService.createSemester(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET ALL SEMESTERS
    @GetMapping
    public ResponseEntity<List<SemesterResponseDTO>> getAllSemesters() {

        List<SemesterResponseDTO> semesters = semesterService.getAllSemesters();

        return ResponseEntity.ok(semesters);
    }

    // GET SEMESTER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SemesterResponseDTO> getSemesterById(
            @PathVariable Long id) {

        SemesterResponseDTO semester = semesterService.getSemesterById(id);

        return ResponseEntity.ok(semester);
    }

    // UPDATE SEMESTER
    @PutMapping("/{id}")
    public ResponseEntity<SemesterResponseDTO> updateSemester(
            @PathVariable Long id,
            @RequestBody SemesterRequestDTO request) {

        SemesterResponseDTO updatedSemester = semesterService.updateSemester(id, request);

        return ResponseEntity.ok(updatedSemester);
    }

    // DELETE SEMESTER
    @DeleteMapping("/{id}")
    public ResponseEntity<SemesterResponseDTO> deleteSemester(
            @PathVariable Long id) {

        SemesterResponseDTO deletedSemester = semesterService.deleteSemester(id);

        return ResponseEntity.ok(deletedSemester);
    }


    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<SemesterResponseDTO>> getSemestersByBranch(
            @PathVariable Long branchId) {

        return ResponseEntity.ok(semesterService.getSemestersByBranch(branchId));
    }
}