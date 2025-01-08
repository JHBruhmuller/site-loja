package com.projeto.loja.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.loja.DTO.AuthDTO;
import com.projeto.loja.DTO.LoginResponseDTO;
import com.projeto.loja.DTO.RegisterDTO;
import com.projeto.loja.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
        return ResponseEntity.ok(userService.authenticate(data));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid AuthDTO data) {
        return ResponseEntity.ok(userService.registerDefault(data));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
        return ResponseEntity.ok(userService.registerAdmin(data));
    }
}
