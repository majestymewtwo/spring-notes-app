package com.springalumni.sairam.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Document(collection = "posts")
public class Posts {
    @Id
    private String id;
    private String content;
    private BigDecimal upVotes;
    private BigDecimal downVotes;
    @DBRef
    private ChatSpace chatSpace;
    @DBRef
    private User user;
    private Set<String> upVotedUsers;
    private Set<String> downVotedUsers;
}
