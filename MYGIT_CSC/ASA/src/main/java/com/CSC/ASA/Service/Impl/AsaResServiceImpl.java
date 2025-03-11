package com.CSC.ASA.Service.Impl;

import com.CSC.ASA.Entity.AsaResEntity;
import com.CSC.ASA.Repo.AsaResRepo;
import com.CSC.ASA.Service.AsaResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AsaResServiceImpl implements AsaResService {

    @Autowired
    private AsaResRepo asaResRepo;

    @Override
    public long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end) {
        return asaResRepo.countSuccessData(clientId, start, end);
    }

    @Override
    public long countFailureData(String clientId, LocalDateTime start, LocalDateTime end) {
        return asaResRepo.countFailureData(clientId, start, end);
    }

    @Override
    public Page<AsaResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return asaResRepo.findSuccessData(clientId, start, end, pageable);
    }

    @Override
    public Page<AsaResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return asaResRepo.findFailureData(clientId, start, end, pageable);
    }

    @Override
    public Map<String, Object> asaSuccDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AsaResEntity> resultPage = findSuccessData(clientId, start, end, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", resultPage.getContent());
        response.put("currentPage", resultPage.getNumber());
        response.put("totalItems", resultPage.getTotalElements());
        response.put("totalPages", resultPage.getTotalPages());

        return response;
    }

    @Override
    public Map<String, Object> asaFailDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AsaResEntity> resultPage = findFailureData(clientId, start, end, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", resultPage.getContent());
        response.put("currentPage", resultPage.getNumber());
        response.put("totalItems", resultPage.getTotalElements());
        response.put("totalPages", resultPage.getTotalPages());

        return response;
    }
}