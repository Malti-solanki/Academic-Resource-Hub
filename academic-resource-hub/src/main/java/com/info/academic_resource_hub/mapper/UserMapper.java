package com.info.academic_resource_hub.mapper;

import com.info.academic_resource_hub.dto.UserDTO;
import com.info.academic_resource_hub.dto.UserRegisterRequestDTO;
import com.info.academic_resource_hub.entity.User;

public class UserMapper {

    public static User toEntity(UserRegisterRequestDTO dto) {

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserDTO toDTO(User user){

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}