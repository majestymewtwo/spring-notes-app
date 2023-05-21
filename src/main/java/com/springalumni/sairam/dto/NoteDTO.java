package com.springalumni.sairam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoteDTO {
    private String id;
    private String title;
    private String content;
    private List<LabelDTO> labels;
    private Boolean pinned;
}
