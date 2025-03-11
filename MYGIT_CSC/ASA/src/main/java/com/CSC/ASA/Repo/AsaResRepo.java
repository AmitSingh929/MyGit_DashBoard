package com.CSC.ASA.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.CSC.ASA.Entity.AsaResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AsaResRepo extends MongoRepository<AsaResEntity, String> {

    // Count matching documents for success
    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] }", count = true)
    long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end);

    // Count matching documents for failure
    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, " +
            "        { $expr: { $lt: [ { $strLenCP: '$err' }, 3 ] } } ] }", count = true)
    long countFailureData(String clientId, LocalDateTime start, LocalDateTime end);

    // Fetch matching documents for success
    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] }")
    Page<AsaResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    // Fetch matching documents for failure
    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, { $expr: { $lt: [ { $strLenCP: '$err' }, 3 ] } } ] }")
    Page<AsaResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);
}