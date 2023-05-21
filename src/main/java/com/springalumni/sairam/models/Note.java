package com.springalumni.sairam.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String title;
    private String content;
    private Boolean pinned;
    @DBRef
    private User author;
}
