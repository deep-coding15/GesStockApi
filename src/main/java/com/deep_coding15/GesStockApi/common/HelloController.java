package com.deep_coding15.GesStockApi.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "GesStock API is running";
    }

    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}
