package com.springalumni.sairam.dto;

import com.springalumni.sairam.models.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String profilePic;
    private String email;
    private Role role;
}
