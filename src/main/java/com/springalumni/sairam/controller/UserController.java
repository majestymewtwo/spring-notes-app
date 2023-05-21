package com.springalumni.sairam.controller;


import com.springalumni.sairam.config.AuthContext;
import com.springalumni.sairam.dto.LabelDTO;
import com.springalumni.sairam.dto.NoteDTO;
import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import com.springalumni.sairam.repository.UserRepository;
import com.springalumni.sairam.service.AuthenticationService;
import com.springalumni.sairam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<NoteDTO> getNotes(){
        return userService.getAllNotes();
    }
    @GetMapping("/{id}")
    public NoteDTO getNote(@PathVariable String id) {
        return userService.getNote(id);
    }
    @PostMapping("/new")
    public void newNote(
            @RequestBody NoteDTO newNote
    ) {
        userService.addNewNote(newNote);
    }
    @PutMapping("/edit")
    public void editMockup(
            @RequestBody NoteDTO noteDTO
    ) {
        userService.updateNote(noteDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteNote(
            @PathVariable String id
    ) {
        userService.deleteNote(id);
    }
    @PutMapping("/togglePin/{id}")
    public void togglePin(
            @PathVariable String id
    ) {
        userService.togglePin(id);
    }
    @PutMapping("/addLabel/{id}")
    public void addLabel(
            @PathVariable String id,
            @RequestBody LabelDTO label
            ) {
        userService.addLabel(id, label);
    }
    @DeleteMapping("/deleteLabel/{id}")
    public void deleteLabel(
            @PathVariable String id
    ) {
        userService.deleteLabel(id);
    }
}
