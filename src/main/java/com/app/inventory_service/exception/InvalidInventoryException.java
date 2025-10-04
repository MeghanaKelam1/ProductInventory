package com.app.inventory_service.exception;

public class InvalidInventoryException extends RuntimeException{
    public InvalidInventoryException(String message){
        super(message);
    }
}
