package com.info.academic_resource_hub.controller;

import com.info.academic_resource_hub.dto.AdminCreateDTO;
import com.info.academic_resource_hub.dto.UserDTO;
import com.info.academic_resource_hub.entity.User;
import com.info.academic_resource_hub.repository.UserRepository;
import com.info.academic_resource_hub.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

        UserDTO user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO){

        UserDTO updatedUser = userService.updateUser(id, userDTO);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody AdminCreateDTO adminDTO){

        UserDTO admin = userService.createAdmin(adminDTO);

        return ResponseEntity.status(201).body(admin);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getLoggedInUser(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName()).orElseThrow();

        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(dto);
    }
}
