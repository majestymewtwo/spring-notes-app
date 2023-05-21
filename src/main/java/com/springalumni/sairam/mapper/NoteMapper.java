package com.springalumni.sairam.mapper;

import com.springalumni.sairam.dto.NoteDTO;
import com.springalumni.sairam.models.Label;
import com.springalumni.sairam.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteMapper {
    private final LabelMapper labelMapper;
    public NoteDTO mapToDTO(Note note, List<Label> labels){
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        noteDTO.setLabels(labels.stream().map(labelMapper::mapToDTO).toList());
        return noteDTO;
    }
    public Note mapToEntity(NoteDTO noteDTO){
        Note note = new Note();
        BeanUtils.copyProperties(noteDTO, note);
        return note;
    }
}
