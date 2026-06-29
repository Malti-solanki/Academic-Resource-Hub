package com.info.academic_resource_hub.controller;

import com.info.academic_resource_hub.dto.ResourceRequestDTO;
import com.info.academic_resource_hub.dto.ResourceResponseDTO;
import com.info.academic_resource_hub.entity.Resource;
import com.info.academic_resource_hub.enums.ResourceType;
import com.info.academic_resource_hub.repository.ResourceRepository;
import com.info.academic_resource_hub.service.ResourceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;
    private final ResourceRepository resourceRepository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResourceResponseDTO> uploadResource(
            @RequestParam String title,
            @RequestParam ResourceType type,
            @RequestParam Long subjectId,
            @RequestParam(required = false) Long uploadedBy,
            @RequestParam MultipartFile file) {

        ResourceRequestDTO request = new ResourceRequestDTO();
        request.setTitle(title);
        request.setType(type);
        request.setSubjectId(subjectId);
        request.setUploadedBy(uploadedBy);

        return ResponseEntity.ok(resourceService.uploadResource(request, file));
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponseDTO>> getAllResources() {

        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> getResourceById(@PathVariable Long id) {

        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<ResourceResponseDTO>> getResourcesBySubject(
            @PathVariable Long subjectId) {

        return ResponseEntity.ok(resourceService.getResourcesBySubject(subjectId));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadResource(@PathVariable Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        try {
            Path path = Paths.get(resource.getFilePath());
            byte[] file = Files.readAllBytes(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + resource.getFileName() + "\"")
                    .body(file);

        } catch (IOException e) {
            throw new RuntimeException("Download failed");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> deleteResource(@PathVariable Long id) {

        return ResponseEntity.ok(resourceService.deleteResource(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResourceResponseDTO> updateResource(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam ResourceType type,
            @RequestParam(required = false) MultipartFile file) {

        ResourceResponseDTO response = resourceService.updateResource(id, title, type, file);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject/{subjectId}/type/{type}")
    public ResponseEntity<List<ResourceResponseDTO>> getResourcesBySubjectAndType(
            @PathVariable Long subjectId,
            @PathVariable ResourceType type) {

        return ResponseEntity.ok(resourceService.getResourcesBySubjectAndType(subjectId, type));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ResourceResponseDTO>> getResourcesByType(
            @PathVariable ResourceType type) {

        return ResponseEntity.ok(resourceService.getResourcesByType(type));
    }
}