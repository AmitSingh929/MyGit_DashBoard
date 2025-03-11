package com.CSC.AUA.Service;

import com.CSC.AUA.Entity.AuaReqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

public interface AuaReqService {
    long countMatchingRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate);

    Page<AuaReqEntity> findRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    public Map<String, Object> getReqDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
}