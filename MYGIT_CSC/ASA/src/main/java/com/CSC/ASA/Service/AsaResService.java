package com.CSC.ASA.Service;

import com.CSC.ASA.Entity.AsaResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

public interface AsaResService {

    long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end);

    long countFailureData(String clientId, LocalDateTime start, LocalDateTime end);

    Page<AsaResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<AsaResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Map<String, Object> asaSuccDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size);

    Map<String, Object> asaFailDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size);
}