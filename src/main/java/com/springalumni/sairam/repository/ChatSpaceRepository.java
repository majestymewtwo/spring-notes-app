package com.springalumni.sairam.repository;

import com.springalumni.sairam.models.ChatSpace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatSpaceRepository extends MongoRepository<ChatSpace, String> {
    Page<ChatSpace> findAllByDomainId(String domainId, Pageable pageable);
}
