package com.springalumni.sairam.dto;

import com.springalumni.sairam.models.Domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
    private String id;
    private String name;
    private DomainDTO domain;
}
