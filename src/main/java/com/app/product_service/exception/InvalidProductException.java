package com.app.product_service.exception;

public class InvalidProductException extends RuntimeException {
    public InvalidProductException(String message){
        super(message);
    }
}
