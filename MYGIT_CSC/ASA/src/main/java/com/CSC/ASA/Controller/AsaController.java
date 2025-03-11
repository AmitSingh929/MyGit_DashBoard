package com.CSC.ASA.Controller;

import com.CSC.ASA.Service.AsaReqService;
import com.CSC.ASA.Service.AsaResService;
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
@RequestMapping("/asa")
@CrossOrigin(origins = "*")
public class AsaController {

    private static final Logger logger = LoggerFactory.getLogger(AsaController.class);

    @Autowired
    private AsaReqService asaReqService;

    @Autowired
    private AsaResService asaResService;

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
            Map<String, Object> response = asaReqService.getReqDataService(clientId, start, end, page, size);
            logger.info("Successfully retrieved requests for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving requests for clientId: {}", clientId, e);
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
            Map<String, Object> response = asaResService.asaSuccDataService(clientId, start, end, page, size);
            logger.info("Successfully retrieved success responses for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving success responses for clientId: {}", clientId, e);
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
            Map<String, Object> response = asaResService.asaFailDataService(clientId, start, end, page, size);
            logger.info("Successfully retrieved failure responses for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving failure responses for clientId: {}", clientId, e);
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
            long requestCount = asaReqService.countMatchingRequests(clientId, start, end);
            long successResponseCount = asaResService.countSuccessData(clientId, start, end);
            long failureResponseCount = asaResService.countFailureData(clientId, start, end);

            Map<String, Long> counts = new HashMap<>();
            counts.put("requestCount", requestCount);
            counts.put("successResponseCount", successResponseCount);
            counts.put("failureResponseCount", failureResponseCount);

            logger.info("Successfully retrieved counts for clientId: {}", clientId);
            return new ResponseEntity<>(counts, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving counts for clientId: {}", clientId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}