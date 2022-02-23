package com.kaya.payroll.model;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Could not find order " + id + ".");
    }

}
