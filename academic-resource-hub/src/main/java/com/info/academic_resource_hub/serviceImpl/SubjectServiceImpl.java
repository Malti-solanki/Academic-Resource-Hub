package com.info.academic_resource_hub.serviceImpl;


import com.info.academic_resource_hub.dto.SubjectRequestDTO;
import com.info.academic_resource_hub.dto.SubjectResponseDTO;
import com.info.academic_resource_hub.entity.Semester;
import com.info.academic_resource_hub.entity.Subject;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.repository.SemesterRepository;
import com.info.academic_resource_hub.repository.SubjectRepository;
import com.info.academic_resource_hub.service.SubjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;

    @Override
    public SubjectResponseDTO createSubject(SubjectRequestDTO request) {

        Semester semester = semesterRepository.findById(request.getSemesterId())
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        Subject subject = Subject.builder()
                .name(request.getName())
                .subjectCode(request.getSubjectCode())
                .semester(semester)
                .build();

        subjectRepository.save(subject);

        return mapToResponse(subject);
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {

        return subjectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResponseDTO getSubjectById(Long id) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        return mapToResponse(subject);
    }

    @Override
    public SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO request) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        Semester semester = semesterRepository.findById(request.getSemesterId())
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found"));

        subject.setName(request.getName());
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSemester(semester);

        subjectRepository.save(subject);

        return mapToResponse(subject);
    }

    @Override
    public SubjectResponseDTO deleteSubject(Long id) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        subjectRepository.delete(subject);

        return mapToResponse(subject);
    }

    @Override
    public List<SubjectResponseDTO> getSubjectsBySemester(Long semesterId) {

        List<Subject> subjects = subjectRepository.findBySemesterId(semesterId);

        return subjects.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SubjectResponseDTO mapToResponse(Subject subject) {

        return SubjectResponseDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .subjectCode(subject.getSubjectCode())
                .semesterId(subject.getSemester().getId())
                .semNumber(subject.getSemester().getSemesterNumber())
                .build();
    }
}
