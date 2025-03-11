package com.CSC.AUA.Service.Impl;

import com.CSC.AUA.Entity.AuaResEntity;
import com.CSC.AUA.Repo.AuaResRepo;
import com.CSC.AUA.Service.AuaResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuaResServiceImpl implements AuaResService {

    @Autowired
    private AuaResRepo auaResRepo;

    @Override
    public long countSuccessData(String clientId, LocalDateTime startDate, LocalDateTime endDate) {
        return auaResRepo.countSuccessData(clientId, startDate, endDate);
    }

    @Override
    public long countFailureData(String clientId, LocalDateTime startDate, LocalDateTime endDate) {
        return auaResRepo.countFailureData(clientId, startDate, endDate);
    }

    @Override
    public Page<AuaResEntity> findSuccessData(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return auaResRepo.findSuccessData(clientId, startDate, endDate, pageable);
    }

    @Override
    public Page<AuaResEntity> findFailureData(String clientId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return auaResRepo.findFailureData(clientId, startDate, endDate, pageable);
    }

    @Override
    public Map<String, Object> auaSuccDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuaResEntity> successPage = findSuccessData(clientId, startDate, endDate, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", successPage.getContent());
        response.put("currentPage", successPage.getNumber());
        response.put("totalItems", successPage.getTotalElements());
        response.put("totalPages", successPage.getTotalPages());

        return response;
    }

    @Override
    public Map<String, Object> auaFailDataService(String clientId, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuaResEntity> failurePage = findFailureData(clientId, startDate, endDate, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", failurePage.getContent());
        response.put("currentPage", failurePage.getNumber());
        response.put("totalItems", failurePage.getTotalElements());
        response.put("totalPages", failurePage.getTotalPages());

        return response;
    }
}