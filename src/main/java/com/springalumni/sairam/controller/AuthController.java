package com.springalumni.sairam.controller;

import com.springalumni.sairam.dto.AuthDTO;
import com.springalumni.sairam.dto.LoginDTO;
import com.springalumni.sairam.dto.RegisterDTO;
import com.springalumni.sairam.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService service;
    @GetMapping
    public String test() {
        return "This works";
    }
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
    @PostMapping("/authenticate/google")
    public ResponseEntity<AuthDTO> googleAuthenticate(
            @RequestParam String token
    ) throws Exception {
        return ResponseEntity.ok(service.verifyOauthToken(token));
    }
}
