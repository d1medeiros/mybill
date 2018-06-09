package com.dmedeiros.mybill.bill.exception;

public class EmptyException extends RuntimeException {
    public EmptyException(String description) {
        super(description + " was not filled.");
    }
}
