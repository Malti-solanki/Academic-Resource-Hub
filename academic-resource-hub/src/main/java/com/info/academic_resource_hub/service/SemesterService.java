package com.info.academic_resource_hub.service;


import com.info.academic_resource_hub.dto.SemesterRequestDTO;
import com.info.academic_resource_hub.dto.SemesterResponseDTO;

import java.util.List;

public interface SemesterService {

    SemesterResponseDTO createSemester(SemesterRequestDTO request);

    List<SemesterResponseDTO> getAllSemesters();

    SemesterResponseDTO getSemesterById(Long id);

    SemesterResponseDTO updateSemester(Long id, SemesterRequestDTO request);

    SemesterResponseDTO deleteSemester(Long id);

    List<SemesterResponseDTO> getSemestersByBranch(Long branchId);

}