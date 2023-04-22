package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<List<User>> findAllByRole(Role role);
    Optional<User> findByEmail(String email);
}
