package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DomainRepository extends MongoRepository<Domain, String> {
}
