package com.springalumni.sairam.controller;


import com.springalumni.sairam.config.AuthContext;
import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import com.springalumni.sairam.repository.UserRepository;
import com.springalumni.sairam.service.AuthenticationService;
import com.springalumni.sairam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    @GetMapping("/getAllStudents")
    public List<UserDTO> getAllStudents() {
        return userService.getAllStudents();
    }
    @GetMapping("/getAllAlumni")
    public List<UserDTO> getAllAlumni() {
        return userService.getAllAlumni();
    }
}
