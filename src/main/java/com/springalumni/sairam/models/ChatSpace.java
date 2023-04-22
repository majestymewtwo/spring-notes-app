package com.springalumni.sairam.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chat-spaces")
public class ChatSpace {
    @Id
    private String id;
    private String name;
    @DBRef
    private Domain domain;
}
