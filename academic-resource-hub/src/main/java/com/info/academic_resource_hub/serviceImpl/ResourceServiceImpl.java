package com.info.academic_resource_hub.serviceImpl;

import com.info.academic_resource_hub.dto.ResourceRequestDTO;
import com.info.academic_resource_hub.dto.ResourceResponseDTO;
import com.info.academic_resource_hub.entity.Resource;
import com.info.academic_resource_hub.entity.Subject;
import com.info.academic_resource_hub.entity.User;
import com.info.academic_resource_hub.enums.ResourceType;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.mapper.ResourceMapper;
import com.info.academic_resource_hub.repository.ResourceRepository;
import com.info.academic_resource_hub.repository.SubjectRepository;
import com.info.academic_resource_hub.repository.UserRepository;
import com.info.academic_resource_hub.service.ResourceService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    private final String uploadDir = "uploads/";

    @Override
    public ResourceResponseDTO uploadResource(ResourceRequestDTO request, MultipartFile file) {

        try {

            Subject subject = subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

            User user = userRepository.findById(request.getUploadedBy())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir, fileName);

            Files.createDirectories(filePath.getParent());

            Files.write(filePath, file.getBytes());

            Resource resource = Resource.builder()
                    .title(request.getTitle())
                    .fileName(fileName)
                    .filePath(filePath.toString())
                    .fileSize(file.getSize())
                    .type(request.getType())
                    .uploadedAt(LocalDateTime.now())
                    .subject(subject)
                    .uploadedBy(user)
                    .build();

            Resource savedResource = resourceRepository.save(resource);

            return ResourceMapper.toDTO(savedResource);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    @Override
    public List<ResourceResponseDTO> getAllResources() {

        return resourceRepository.findAll()
                .stream()
                .map(ResourceMapper::toDTO)
                .toList();
    }

    @Override
    public ResourceResponseDTO getResourceById(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return ResourceMapper.toDTO(resource);
    }

    @Override
    public List<ResourceResponseDTO> getResourcesBySubject(Long subjectId) {

        return resourceRepository.findBySubjectId(subjectId)
                .stream()
                .map(ResourceMapper::toDTO)
                .toList();
    }

    @Override
    public byte[] downloadResource(Long id) {

        try {

            Resource resource = resourceRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

            Path path = Paths.get(resource.getFilePath());

            return Files.readAllBytes(path);

        } catch (IOException e) {
            throw new RuntimeException("File download failed", e);
        }
    }

    @Override
    public ResourceResponseDTO deleteResource(Long id) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        try {
            Path path = Paths.get(resource.getFilePath());
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("File deletion failed", e);
        }

        resourceRepository.delete(resource);

        return ResourceMapper.toDTO(resource);
    }

    @Override
    public ResourceResponseDTO updateResource(Long id, String title, ResourceType type, MultipartFile file) {

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        // update metadata
        resource.setTitle(title);
        resource.setType(type);

        try {

            // if new file uploaded
            if (file != null && !file.isEmpty()) {

                //  DELETE OLD FILE FIRST
                if (resource.getFilePath() != null) {
                    File oldFile = new File(resource.getFilePath());
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }

                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File directory = new File(uploadDir);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                resource.setFileName(fileName);
                resource.setFilePath(filePath.toString());
                resource.setFileSize(file.getSize());
            }

        } catch (IOException e) {
            throw new RuntimeException("File update failed", e);
        }

        Resource updatedResource = resourceRepository.save(resource);

        return ResourceMapper.toDTO(updatedResource);
    }

    public List<ResourceResponseDTO> getResourcesBySubjectAndType(Long subjectId, ResourceType type) {

        List<Resource> resources = resourceRepository.findBySubjectIdAndType(subjectId, type);

        return resources.stream().map(resource -> ResourceResponseDTO.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .fileName(resource.getFileName())
                .fileSize(resource.getFileSize())
                .type(resource.getType())
                .uploadedAt(resource.getUploadedAt())
                .subjectId(resource.getSubject().getId())
                .subjectName(resource.getSubject().getName())
                .uploadedBy(resource.getUploadedBy() != null ? resource.getUploadedBy().getId() : null)
                .build()
        ).collect(Collectors.toList());
    }

    public List<ResourceResponseDTO> getResourcesByType(ResourceType type) {

        List<Resource> resources = resourceRepository.findByType(type);

        return resources.stream().map(resource -> ResourceResponseDTO.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .fileName(resource.getFileName())
                .fileSize(resource.getFileSize())
                .type(resource.getType())
                .uploadedAt(resource.getUploadedAt())
                .subjectId(resource.getSubject().getId())
                .subjectName(resource.getSubject().getName())
                .uploadedBy(resource.getUploadedBy() != null ? resource.getUploadedBy().getId() : null)
                .build()
        ).toList();
    }
}