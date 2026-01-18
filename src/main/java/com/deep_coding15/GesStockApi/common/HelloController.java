package com.deep_coding15.GesStockApi.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /** 
     * @return String
     */
    @GetMapping("/")
    public String home() {
        return "GesStock API is running";
    }

    /** 
     * @return String
     */
    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}
