package com.springalumni.sairam.service;

import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.exception.ApiException;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import com.springalumni.sairam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllStudents() {
        List<User> students = userRepository.findAllByRole(Role.ROLE_STUDENT).orElseThrow();
        return students.stream().map(userMapper::mapToDTO).toList();
    }
    public List<UserDTO> getAllAlumni() {
        List<User> students = userRepository.findAllByRole(Role.ROLE_ALUMNI).orElseThrow();
        return students.stream().map(userMapper::mapToDTO).toList();
    }
}
