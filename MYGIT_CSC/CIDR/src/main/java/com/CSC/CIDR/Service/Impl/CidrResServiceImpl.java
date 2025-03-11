package com.CSC.CIDR.Service.Impl;

import com.CSC.CIDR.Entity.CidrResEntity;
import com.CSC.CIDR.Repo.CidrResRepo;
import com.CSC.CIDR.Service.CidrResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CidrResServiceImpl implements CidrResService {

    @Autowired
    private CidrResRepo cidrResRepo;

    @Override
    public long countSuccessData(String clientId, LocalDateTime start, LocalDateTime end) {
        return cidrResRepo.countSuccessData(clientId, start, end);
    }

    @Override
    public long countFailureData(String clientId, LocalDateTime start, LocalDateTime end) {
        return cidrResRepo.countFailureData(clientId, start, end);
    }

    @Override
    public Page<CidrResEntity> findSuccessData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return cidrResRepo.findSuccessData(clientId, start, end, pageable);
    }

    @Override
    public Page<CidrResEntity> findFailureData(String clientId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return cidrResRepo.findFailureData(clientId, start, end, pageable);
    }

    @Override
    public Map<String, Object> cidrSuccDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CidrResEntity> pageResult = findSuccessData(clientId, start, end, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("data", pageResult.getContent());
        response.put("totalElements", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());
        response.put("currentPage", pageResult.getNumber());
        return response;
    }

    @Override
    public Map<String, Object> cidrFailDataService(String clientId, LocalDateTime start, LocalDateTime end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CidrResEntity> pageResult = findFailureData(clientId, start, end, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("data", pageResult.getContent());
        response.put("totalElements", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());
        response.put("currentPage", pageResult.getNumber());
        return response;
    }
}