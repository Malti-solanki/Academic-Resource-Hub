package com.info.academic_resource_hub.serviceImpl;

import com.info.academic_resource_hub.config.JwtService;
import com.info.academic_resource_hub.dto.AuthResponseDTO;
import com.info.academic_resource_hub.dto.LoginRequestDTO;
import com.info.academic_resource_hub.dto.UserRegisterRequestDTO;
import com.info.academic_resource_hub.entity.User;
import com.info.academic_resource_hub.enums.Role;
import com.info.academic_resource_hub.exception.ResourceNotFoundException;
import com.info.academic_resource_hub.mapper.UserMapper;
import com.info.academic_resource_hub.repository.UserRepository;
import com.info.academic_resource_hub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO register(UserRegisterRequestDTO request) {

        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Default role
        user.setRole(Role.STUDENT);

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDTO(token, user.getRole().name());
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new ResourceNotFoundException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDTO(token, user.getRole().name());
    }
}