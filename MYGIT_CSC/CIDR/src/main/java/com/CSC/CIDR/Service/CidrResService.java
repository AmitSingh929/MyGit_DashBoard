package com.CSC.CIDR.Service;

import com.CSC.CIDR.Entity.CidrResEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

public interface CidrResService {
    long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end);

    long countFailureData(String clientId, LocalDateTime start, LocalDateTime end);

    Page<CidrResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<CidrResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Map<String, Object> cidrSuccDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size);

    Map<String, Object> cidrFailDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size);
}
