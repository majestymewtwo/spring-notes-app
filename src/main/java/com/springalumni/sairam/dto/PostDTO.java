package com.springalumni.sairam.dto;

import com.springalumni.sairam.models.ChatSpace;
import com.springalumni.sairam.models.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private String id;
    private String content;
    private BigDecimal upVotes;
    private BigDecimal downVotes;
    private UserDTO user;
    private List<RepliesDTO> replies;
}
