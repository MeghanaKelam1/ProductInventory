package com.app.inventory_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining( ", "));
        log.error("Validation failed :{}",errorMessage);
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST,errorMessage),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidInventoryException.class)
    public ResponseEntity<ApiError> handleInvalidInventoryException(InvalidInventoryException ex){
        log.error("Invalid inventory exception: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ApiError> handleInventoryNotFoundException(InventoryNotFoundException ex){
        log.error("Inventory not found: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ApiError> handleGenericExceptions(Exception ex){
        log.error("Unhandled exception occurred", ex);
        return new ResponseEntity<>(new ApiError( HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
