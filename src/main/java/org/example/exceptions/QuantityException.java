package org.example.exceptions;

public class QuantityException extends Exception{
    private String customer;
    private String product;

    public QuantityException(String customer, String product) {
        this.customer = customer;
        this.product = product;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProduct() {
        return product;
    }

    public QuantityException() {
    }

    public QuantityException(String message) {
        super(message);
    }
}
