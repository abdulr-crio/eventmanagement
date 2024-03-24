package com.crio.eventmanagement.controller.exchange.responses;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBodyResponse {
    
    private Map<Object, String> body;

    public ErrorBodyResponse(String message) {
        body = new HashMap<>();
        body.put("message", message);
    }
}
