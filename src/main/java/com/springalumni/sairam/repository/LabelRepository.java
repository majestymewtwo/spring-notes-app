package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LabelRepository extends MongoRepository<Label, String> {
    List<Label> findAllByNoteId(String id);
}
