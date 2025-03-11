package com.CSC.ASA.Service.Impl;

import com.CSC.ASA.Entity.AsaReqEntity;
import com.CSC.ASA.Repo.AsaReqRepo;
import com.CSC.ASA.Service.AsaReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AsaReqServiceImpl implements AsaReqService {

    @Autowired
    private AsaReqRepo asaReqRepo;

    @Override
    public long countMatchingRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate) {
        return asaReqRepo.countByClientIdAndDateRange(clientId, startDate, endDate);
    }

    @Override
    public Page<AsaReqEntity> findRequests(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return asaReqRepo.findByClientIdAndDateRange(clientId, startDate, endDate, pageable);
    }

    @Override
    public Map<String, Object> getReqDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AsaReqEntity> resultPage = findRequests(clientId, startDate, endDate, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", resultPage.getContent());
        response.put("currentPage", resultPage.getNumber());
        response.put("totalItems", resultPage.getTotalElements());
        response.put("totalPages", resultPage.getTotalPages());

        return response;
    }
}