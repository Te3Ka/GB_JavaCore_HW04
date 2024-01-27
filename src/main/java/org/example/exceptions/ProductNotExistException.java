package org.example.exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException() {
    }

    public ProductNotExistException(String message) {
        super(message);
    }
}
