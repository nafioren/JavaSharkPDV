package com.proyecto.JavaSharkPDV;

import com.proyecto.JavaSharkPDV.exception.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test-specific-exception")
    public String triggerSpecificException() {
        throw new GlobalExceptionHandler.SpecificException("Specific Exception occurred");
    }

    @GetMapping("/test-generic-exception")
    public String triggerGenericException() {
        throw new RuntimeException("Generic Exception occurred");
    }
}
