package com.proyecto.JavaSharkPDV.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpecificException.class)
    public ResponseEntity<String> handleSpecificException(SpecificException ex) {
        // Manejar la excepción específica
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Manejar todas las demás excepciones
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
    // Define una excepción específica
    public static class SpecificException extends RuntimeException {
        public SpecificException(String message) {
            super(message);
        }
    }
}



