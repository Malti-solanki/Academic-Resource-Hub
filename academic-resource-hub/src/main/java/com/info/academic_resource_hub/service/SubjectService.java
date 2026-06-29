package com.info.academic_resource_hub.service;


import com.info.academic_resource_hub.dto.SubjectRequestDTO;
import com.info.academic_resource_hub.dto.SubjectResponseDTO;

import java.util.List;

public interface SubjectService {

    SubjectResponseDTO createSubject(SubjectRequestDTO request);

    List<SubjectResponseDTO> getAllSubjects();

    SubjectResponseDTO getSubjectById(Long id);

    SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO request);

    SubjectResponseDTO deleteSubject(Long id);

    List<SubjectResponseDTO> getSubjectsBySemester(Long semesterId);
}