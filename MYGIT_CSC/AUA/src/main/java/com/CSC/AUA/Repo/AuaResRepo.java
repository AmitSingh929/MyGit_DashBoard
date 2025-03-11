package com.CSC.AUA.Repo;

import com.CSC.AUA.Entity.AuaResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

public interface AuaResRepo extends MongoRepository<AuaResEntity, String> {

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] }", count = true)
    long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end);

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, " +
            "        { $expr: { $lt: [ { $strLenCP: '$err' }, 3 ] } } ] }", count = true)
    long countFailureData(String clientId, LocalDateTime start, LocalDateTime end);

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] }")
    Page<AuaResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query(value = "{ 'client_id': ?0, 'creation_date': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, { $expr: { $lt: [ { $strLenCP: '$err' }, 3 ] } } ] }")
    Page<AuaResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

}

