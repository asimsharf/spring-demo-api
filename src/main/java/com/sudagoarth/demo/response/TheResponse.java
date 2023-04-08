package com.sudagoarth.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class TheResponse {
private String message;

    public static ResponseEntity<Object> getResponse(String message, HttpStatus status, Object data, Integer code) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("http_status", status);
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }
}
