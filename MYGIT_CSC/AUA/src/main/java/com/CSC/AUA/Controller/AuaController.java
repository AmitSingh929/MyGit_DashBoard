package com.CSC.AUA.Controller;

import com.CSC.AUA.Service.AuaReqService;
import com.CSC.AUA.Service.AuaResService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aua")
@CrossOrigin(origins = "*")
public class AuaController {

    private static final Logger logger = LoggerFactory.getLogger(AuaController.class);

    @Autowired
    private AuaReqService auaReqService;

    @Autowired
    private AuaResService auaResService;

    @GetMapping("/requests")
    public ResponseEntity<Map<String, Object>> getRequests(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get requests for clientId: {}, startDate: {}, endDate: {}, page: {}, size: {}", clientId, startDate, endDate, page, size);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> response = auaReqService.getReqDataService(clientId, start, end, page, size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while getting requests", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/success")
    public ResponseEntity<Map<String, Object>> getSuccessResponses(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get success responses for clientId: {}, startDate: {}, endDate: {}, page: {}, size: {}", clientId, startDate, endDate, page, size);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> response = auaResService.auaSuccDataService(clientId, start, end, page, size);
            logger.info("Successfully retrieved requests for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while getting success responses", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/failure")
    public ResponseEntity<Map<String, Object>> getFailureResponses(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get failure responses for clientId: {}, startDate: {}, endDate: {}, page: {}, size: {}", clientId, startDate, endDate, page, size);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> response = auaResService.auaFailDataService(clientId, start, end, page, size);
            logger.info("Successfully retrieved requests for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while getting failure responses", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        logger.info("Received request to get counts for clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            long requestCount = auaReqService.countMatchingRequests(clientId, start, end);
            long successResponseCount = auaResService.countSuccessData(clientId, start, end);
            long failureResponseCount = auaResService.countFailureData(clientId, start, end);

            Map<String, Long> counts = new HashMap<>();
            counts.put("requestCount", requestCount);
            counts.put("successResponseCount", successResponseCount);
            counts.put("failureResponseCount", failureResponseCount);
            logger.info("Successfully retrieved requests for clientId: {}", clientId);
            return new ResponseEntity<>(counts, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while getting counts", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}