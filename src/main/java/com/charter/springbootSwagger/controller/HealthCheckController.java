package com.charter.springbootSwagger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    @GetMapping(value = "/healthCheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> healthCheck(@RequestParam(required = false) String format) {
        Map<String, Object> response = new HashMap<>();

        if ("short".equalsIgnoreCase(format)) {
            response.put("status", "OK");
            return ResponseEntity.ok(response); // 200 OK
        } else if ("full".equalsIgnoreCase(format)) {
            // Format current time in ISO 8601 format (YYYY-MM-DDTHH:MM:SSZ)
            String formattedTime = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now());

            response.put("currentTime", formattedTime);
            response.put("status", "OK");
            return ResponseEntity.ok(response); // 200 OK
        } else {
            response.put("error", "Invalid format. Use 'short' or 'full'.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response); // 400 Bad Request
        }
    }
}