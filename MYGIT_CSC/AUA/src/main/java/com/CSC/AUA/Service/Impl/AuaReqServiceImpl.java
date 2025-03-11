package com.CSC.AUA.Service.Impl;

import com.CSC.AUA.Entity.AuaReqEntity;
import com.CSC.AUA.Repo.AuaReqRepo;
import com.CSC.AUA.Service.AuaReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuaReqServiceImpl implements AuaReqService {

    @Autowired
    private  AuaReqRepo auaReqRepo;

    @Override
    public long countMatchingRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate) {
        return auaReqRepo.countByClientIdAndDateRange(clientId, startDate, endDate);
    }

    @Override
    public Page<AuaReqEntity> findRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return auaReqRepo.findByClientIdAndDateRange(clientId, startDate, endDate, pageable);
    }

    @Override
    public Map<String, Object> getReqDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuaReqEntity> requestPage = findRequests(clientId, startDate, endDate, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", requestPage.getContent());
        response.put("currentPage", requestPage.getNumber());
        response.put("totalItems", requestPage.getTotalElements());
        response.put("totalPages", requestPage.getTotalPages());

        return response;
    }
}