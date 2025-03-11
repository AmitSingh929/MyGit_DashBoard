package com.CSC.AUA.Service;

import com.CSC.AUA.Entity.AuaResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

public interface AuaResService {
    long countSuccessData(String clientId, LocalDateTime startDate, LocalDateTime endDate);
    long countFailureData(String clientId, LocalDateTime startDate, LocalDateTime endDate);
    Page<AuaResEntity> findSuccessData(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Page<AuaResEntity> findFailureData(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Map<String, Object> auaSuccDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
    Map<String, Object> auaFailDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
}