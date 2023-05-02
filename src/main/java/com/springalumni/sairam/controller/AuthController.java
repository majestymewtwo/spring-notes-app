package com.springalumni.sairam.controller;

import com.springalumni.sairam.dto.AuthDTO;
import com.springalumni.sairam.dto.LoginDTO;
import com.springalumni.sairam.dto.RegisterDTO;
import com.springalumni.sairam.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(
            @RequestBody RegisterDTO request
    ) throws Exception {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthDTO> authenticate(
            @RequestBody LoginDTO request
    ) throws Exception {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
