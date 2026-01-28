package com.deep_coding15.GesStockApi.common.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deep_coding15.GesStockApi.common.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Entity_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(// The `EntityAlreadyExistsException` is a custom exception class that is used
                      // to
    // handle cases where an entity already exists in the system when it should not.
    // In the provided code snippet, the `handleEntityAlreadyExists` method is an
    // exception handler specifically designed to catch instances of
    // `EntityAlreadyExistsException` being thrown. When this exception occurs, it
    // creates an `ApiError` object with a specific error message and status code,
    // and then returns a `ResponseEntity` with the error details and an HTTP status
    // of `CONFLICT` (409). This allows the application to handle the situation
    // where
    // an entity is being created or modified but already exists in the system.
    EntityAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEntityAlreadyExists(
            EntityAlreadyExistsException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Entity_ALREADY_EXISTS",
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
            );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                message,
                request.getRequestURI(),
                LocalDateTime.now());

        return ResponseEntity.badRequest().body(error);
    }

    // Ne jamais exposer les ex.getMessage() pour les erreurs 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "Une erreur interne est survenue",
                request.getRequestURI(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
