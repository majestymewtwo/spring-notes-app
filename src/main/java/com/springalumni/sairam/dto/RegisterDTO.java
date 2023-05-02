package com.springalumni.sairam.dto;

import com.springalumni.sairam.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String profilePic;
    private String collegeName;
    private String degree;
    private String department;
    private String yearOfStudy;
    private String email;
    private String password;
    private Role role;
}
