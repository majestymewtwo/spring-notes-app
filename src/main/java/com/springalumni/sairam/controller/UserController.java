package com.springalumni.sairam.controller;


import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import com.springalumni.sairam.repository.UserRepository;
import com.springalumni.sairam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getAllStudents")
    public List<UserDTO> getAllStudents() {
        return userService.getAllStudents();
    }
    @GetMapping("/getAllAlumni")
    public List<UserDTO> getAllAlumni() {
        return userService.getAllAlumni();
    }
}
