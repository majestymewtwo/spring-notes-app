package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostsRepository extends MongoRepository<Posts, String> {
}
