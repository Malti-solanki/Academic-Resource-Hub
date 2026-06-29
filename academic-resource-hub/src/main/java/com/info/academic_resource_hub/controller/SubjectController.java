package com.info.academic_resource_hub.controller;
import com.info.academic_resource_hub.dto.SubjectRequestDTO;
import com.info.academic_resource_hub.dto.SubjectResponseDTO;
import com.info.academic_resource_hub.service.SubjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(
            @RequestBody SubjectRequestDTO request) {

        return ResponseEntity.ok(subjectService.createSubject(request));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {

        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable Long id) {

        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(
            @PathVariable Long id,
            @RequestBody SubjectRequestDTO request) {

        return ResponseEntity.ok(subjectService.updateSubject(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> deleteSubject(@PathVariable Long id) {

        return ResponseEntity.ok(subjectService.deleteSubject(id));
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectsBySemester(
            @PathVariable Long semesterId) {

        return ResponseEntity.ok(subjectService.getSubjectsBySemester(semesterId));
    }
}
