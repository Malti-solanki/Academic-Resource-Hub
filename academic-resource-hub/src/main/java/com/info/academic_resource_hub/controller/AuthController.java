package com.info.academic_resource_hub.controller;

import com.info.academic_resource_hub.dto.AuthResponseDTO;
import com.info.academic_resource_hub.dto.LoginRequestDTO;
import com.info.academic_resource_hub.dto.UserRegisterRequestDTO;
import com.info.academic_resource_hub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody UserRegisterRequestDTO request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Welcome Admin 🎉";
    }

    @GetMapping("/student/test")
    public String studentTest() {
        return "Welcome Student 🎉";
    }

}