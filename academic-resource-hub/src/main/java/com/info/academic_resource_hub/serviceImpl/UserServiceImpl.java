package com.info.academic_resource_hub.serviceImpl;
import com.info.academic_resource_hub.dto.AdminCreateDTO;
import com.info.academic_resource_hub.dto.UserDTO;
import com.info.academic_resource_hub.entity.User;
import com.info.academic_resource_hub.enums.Role;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.mapper.UserMapper;
import com.info.academic_resource_hub.repository.UserRepository;
import com.info.academic_resource_hub.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(userDTO.getName() != null) user.setName(userDTO.getName());
        if(userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if(userDTO.getRole() != null) user.setRole(userDTO.getRole());

        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(id);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO createAdmin(AdminCreateDTO adminDTO) {

        User user = User.builder()
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(passwordEncoder.encode(adminDTO.getPassword()))
                .role(Role.ADMIN)
                .build();

        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }
}
