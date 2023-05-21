package com.springalumni.sairam.service;

import com.springalumni.sairam.config.AuthContext;
import com.springalumni.sairam.dto.LabelDTO;
import com.springalumni.sairam.dto.NoteDTO;
import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.exception.ApiException;
import com.springalumni.sairam.mapper.LabelMapper;
import com.springalumni.sairam.mapper.NoteMapper;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Label;
import com.springalumni.sairam.models.Note;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import com.springalumni.sairam.repository.LabelRepository;
import com.springalumni.sairam.repository.NoteRepository;
import com.springalumni.sairam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final LabelRepository labelRepository;
    private final UserMapper userMapper;
    private final NoteMapper noteMapper;
    private final LabelMapper labelMapper;
    public UserDTO saveUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            return userMapper.mapToDTO(
                    userRepository.findByEmail(userDTO.getEmail()).get()
            );
        }
        User user = User.builder().build();
        BeanUtils.copyProperties(userDTO, user);
        return userMapper.mapToDTO(userRepository.save(user));
    }
    public List<NoteDTO> getAllNotes() {
        List<NoteDTO> userNotes = new ArrayList<>();
        User author = userRepository.findByEmail(AuthContext.getCurrentUser()).orElseThrow();
        List<Note> notes = noteRepository.findAllByAuthorId(author.getId());
        for(Note note : notes){
            List<Label> labels = labelRepository.findAllByNoteId(note.getId());
            userNotes.add(noteMapper.mapToDTO(note, labels));
        }
        return userNotes;
    }
    public NoteDTO getNote(String id) {
        Note note = noteRepository.findById(id).orElseThrow();
        List<Label> labels = labelRepository.findAllByNoteId(note.getId());
        return noteMapper.mapToDTO(note, labels);
    }
    public void addNewNote(NoteDTO noteDTO) {
        Note newNote = noteMapper.mapToEntity(noteDTO);
        User user = userRepository.findByEmail(AuthContext.getCurrentUser()).orElseThrow();
        newNote.setAuthor(user);
        newNote =  noteRepository.save(newNote);
        for(LabelDTO labelDTO : noteDTO.getLabels()){
            Label newLabel  = labelMapper.mapToEntity(labelDTO);
            newLabel.setNote(newNote);
            labelRepository.save(newLabel);
        }
    }
    public void updateNote(NoteDTO noteDTO) {
        Note note = noteMapper.mapToEntity(noteDTO);
        note.setAuthor(userRepository.findByEmail(AuthContext.getCurrentUser()).orElseThrow());
        noteRepository.save(note);
    }
    public void deleteNote(String id) {
        Note note = noteRepository.findById(id).orElseThrow();
        labelRepository.deleteAll(labelRepository.findAllByNoteId(note.getId()));
        noteRepository.delete(note);
    }
    public void togglePin(String id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setPinned(!note.getPinned());
        noteRepository.save(note);
    }
    public void addLabel(String id, LabelDTO labelDTO) {
        Note note = noteRepository.findById(id).orElseThrow();
        Label newLabel = labelMapper.mapToEntity(labelDTO);
        newLabel.setNote(note);
        labelRepository.save(newLabel);
    }
    public void deleteLabel(String id) {
        labelRepository.deleteById(id);
    }
}
