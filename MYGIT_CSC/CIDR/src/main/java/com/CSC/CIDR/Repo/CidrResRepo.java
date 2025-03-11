package com.CSC.CIDR.Repo;
import com.CSC.CIDR.Entity.CidrResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDateTime;

public interface CidrResRepo extends MongoRepository<CidrResEntity, String> {

    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $and: [ { 'err': { $exists: true } }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] } ] }", count = true)
    long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end);

    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, " +
            "        { $expr: { $lt: [ { $strLenCP: '$err' }, 3 ] } } ] }", count = true)
    long countFailureData(String clientId, LocalDateTime start, LocalDateTime end);

    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$or: [ { 'ret': 'y' }, { $and: [ { 'err': { $exists: true } }, { $expr: { $gt: [ { $strLenCP: '$err' }, 3 ] } } ] } ] }")
    Page<CidrResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query(value = "{ 'clientId': ?0, 'creationDate': { $gte: ?1, $lte: ?2 }, " +
            "$and: [ { $or: [ { 'ret': 'n' }, { 'err': { $ne: '' } } ] }, " +
            "        { $expr: { $lte: [ { $strLenCP: '$err' }, 2 ] } } ] }")
    Page<CidrResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);
}