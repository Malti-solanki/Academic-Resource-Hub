package com.info.academic_resource_hub.mapper;
import com.info.academic_resource_hub.dto.SemesterResponseDTO;
import com.info.academic_resource_hub.entity.Semester;

public class SemesterMapper {

    public static SemesterResponseDTO toDTO(Semester semester) {

        SemesterResponseDTO dto = new SemesterResponseDTO();

        dto.setId(semester.getId());
        dto.setSemNumber(semester.getSemesterNumber());
        dto.setBranchId(semester.getBranch().getId());
        dto.setBranchName(semester.getBranch().getName());

        return dto;
    }
}
