package com.info.academic_resource_hub.service;

import com.info.academic_resource_hub.dto.AuthResponseDTO;
import com.info.academic_resource_hub.dto.LoginRequestDTO;
import com.info.academic_resource_hub.dto.UserRegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(UserRegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);

}
