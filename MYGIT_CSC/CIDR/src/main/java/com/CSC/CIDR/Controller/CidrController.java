package com.CSC.CIDR.Controller;

import com.CSC.CIDR.Service.CidrResService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cidr")
@CrossOrigin(origins = "*")
public class CidrController {

    private static final Logger logger = LoggerFactory.getLogger(CidrController.class);

    @Autowired
    private CidrResService cidrResService;

    @GetMapping("/requests")
    public ResponseEntity<Map<String, Object>> getRequests(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request for clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> successResponse = cidrResService.cidrSuccDataService(clientId, start, end, page, size);
            Map<String, Object> failureResponse = cidrResService.cidrFailDataService(clientId, start, end, page, size);

            List<Object> combinedData = new ArrayList<>();
            combinedData.addAll((List<Object>) successResponse.get("data"));
            combinedData.addAll((List<Object>) failureResponse.get("data"));

            Map<String, Object> combinedResponse = new HashMap<>();
            combinedResponse.put("data", combinedData);
            combinedResponse.put("totalElements", (Long) successResponse.get("totalElements") + (Long) failureResponse.get("totalElements"));
            combinedResponse.put("totalPages", Math.max((Integer) successResponse.get("totalPages"), (Integer) failureResponse.get("totalPages")));
            combinedResponse.put("currentPage", page);

            logger.info("Successfully fetched request data for clientId: {}", clientId);
            return new ResponseEntity<>(combinedResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching request data for clientId: {}", clientId, e);
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
        logger.info("Received success response request for clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> response = cidrResService.cidrSuccDataService(clientId, start, end, page, size);
            logger.info("Successfully fetched success response data for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching success response data for clientId: {}", clientId, e);
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
        logger.info("Received failure response request for clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            Map<String, Object> response = cidrResService.cidrFailDataService(clientId, start, end, page, size);
            logger.info("Successfully fetched failure response data for clientId: {}", clientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching failure response data for clientId: {}", clientId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts(
            @RequestParam String clientId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        logger.info("Received counts request for clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);
        try {
            LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);
            long successResponseCount = cidrResService.countSuccessData(clientId, start, end);
            long failureResponseCount = cidrResService.countFailureData(clientId, start, end);
            long requestCount = successResponseCount + failureResponseCount;

            Map<String, Long> counts = new HashMap<>();
            counts.put("requestCount", requestCount);
            counts.put("successResponseCount", successResponseCount);
            counts.put("failureResponseCount", failureResponseCount);

            logger.info("Successfully fetched counts for clientId: {}", clientId);
            return new ResponseEntity<>(counts, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching counts for clientId: {}", clientId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}