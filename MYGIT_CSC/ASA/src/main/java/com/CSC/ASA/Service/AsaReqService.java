package com.CSC.ASA.Service;

import com.CSC.ASA.Entity.AsaReqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

public interface AsaReqService {
    long countMatchingRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate);
    Page<AsaReqEntity> findRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Map<String, Object> getReqDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
}