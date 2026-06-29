package com.info.academic_resource_hub.service;

import com.info.academic_resource_hub.dto.AdminCreateDTO;
import com.info.academic_resource_hub.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    UserDTO deleteUser(Long id);

    UserDTO createAdmin(AdminCreateDTO adminDTO);

}