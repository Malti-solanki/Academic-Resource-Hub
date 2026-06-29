package com.info.academic_resource_hub.serviceImpl;

import com.info.academic_resource_hub.dto.SemesterRequestDTO;
import com.info.academic_resource_hub.dto.SemesterResponseDTO;
import com.info.academic_resource_hub.entity.Branch;
import com.info.academic_resource_hub.entity.Semester;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.mapper.SemesterMapper;
import com.info.academic_resource_hub.repository.BranchRepository;
import com.info.academic_resource_hub.repository.SemesterRepository;
import com.info.academic_resource_hub.service.SemesterService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;
    private final BranchRepository branchRepository;

    // CREATE SEMESTER
    @Override
    public SemesterResponseDTO createSemester(SemesterRequestDTO request) {

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + request.getBranchId()));

        Semester semester = new Semester();
        semester.setSemesterNumber(request.getSemNumber());
        semester.setBranch(branch);

        Semester savedSemester = semesterRepository.save(semester);

        return SemesterMapper.toDTO(savedSemester);
    }

    // GET ALL SEMESTERS
    @Override
    public List<SemesterResponseDTO> getAllSemesters() {

        List<Semester> semesters = semesterRepository.findAll();

        return semesters.stream()
                .map(SemesterMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET SEMESTER BY ID
    @Override
    public SemesterResponseDTO getSemesterById(Long id) {

        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));

        return SemesterMapper.toDTO(semester);
    }

    // UPDATE SEMESTER
    @Override
    public SemesterResponseDTO updateSemester(Long id, SemesterRequestDTO request) {

        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + request.getBranchId()));

        semester.setSemesterNumber(request.getSemNumber());
        semester.setBranch(branch);

        Semester updatedSemester = semesterRepository.save(semester);

        return SemesterMapper.toDTO(updatedSemester);
    }

    // DELETE SEMESTER
    @Override
    public SemesterResponseDTO deleteSemester(Long id) {

        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found with id: " + id));

        semesterRepository.delete(semester);

        return SemesterMapper.toDTO(semester);
    }

    //get semester by branch id
    @Override
    public List<SemesterResponseDTO> getSemestersByBranch(Long branchId) {

        List<Semester> semesters = semesterRepository.findByBranchId(branchId);

        return semesters.stream()
                .map(SemesterMapper::toDTO)
                .collect(Collectors.toList());
    }
}