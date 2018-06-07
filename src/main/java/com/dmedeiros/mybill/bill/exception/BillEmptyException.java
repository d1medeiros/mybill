package com.dmedeiros.mybill.bill.exception;

public class BillEmptyException extends RuntimeException {
    public BillEmptyException () {
        super("Bill was not filled.");
    }
}
