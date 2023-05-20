package com.springalumni.sairam.dto;

import com.springalumni.sairam.models.Posts;
import com.springalumni.sairam.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Getter
@Setter
public class RepliesDTO {
    private String id;
    private String content;
    private BigDecimal upVotes;
    private BigDecimal downVotes;
    private RepliesDTO replies;
    private UserDTO user;
}
