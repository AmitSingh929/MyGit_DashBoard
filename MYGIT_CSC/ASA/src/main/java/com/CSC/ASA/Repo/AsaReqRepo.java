package com.CSC.ASA.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.CSC.ASA.Entity.AsaReqEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AsaReqRepo extends MongoRepository<AsaReqEntity, String> {

    @Query("{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 } }")
    Page<AsaReqEntity> findByClientIdAndDateRange(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 } }", count = true)
    long countByClientIdAndDateRange(String clientId, LocalDateTime startDate, LocalDateTime endDate);
}