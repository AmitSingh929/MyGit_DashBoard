package com.CSC.AUA.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.CSC.AUA.Entity.AuaReqEntity;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

public interface AuaReqRepo extends MongoRepository<AuaReqEntity, String> {
    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 } }")
    Page<AuaReqEntity> findByClientIdAndDateRange(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 } }", count = true)
    long countByClientIdAndDateRange(String clientId, LocalDateTime startDate, LocalDateTime endDate);
}
