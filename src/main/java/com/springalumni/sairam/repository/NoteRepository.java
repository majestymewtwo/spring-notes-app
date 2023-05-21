package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByAuthorId(String id);
}
