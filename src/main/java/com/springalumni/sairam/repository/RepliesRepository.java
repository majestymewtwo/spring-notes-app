package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Replies;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepliesRepository extends MongoRepository<Replies, String> {
    List<Replies> findAllByPosts_Id(String postId);
}
