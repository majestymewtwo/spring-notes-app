package com.springalumni.sairam.controller;

import com.springalumni.sairam.dto.AuthDTO;
import com.springalumni.sairam.dto.LoginDTO;
import com.springalumni.sairam.dto.RegisterDTO;
import com.springalumni.sairam.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService service;
    @GetMapping
    public String test() {
        return "This works";
    }
    @GetMapping("/test")
    public String jsonTest() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("status", "working");
        return obj.toString();
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
