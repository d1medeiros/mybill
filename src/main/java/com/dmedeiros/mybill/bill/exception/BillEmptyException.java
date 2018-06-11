package com.dmedeiros.mybill.bill.exception;

public class BillEmptyException extends RuntimeException {

    public BillEmptyException() {
    }

    public BillEmptyException (String name) {
        super("MYBILL  >>>>" + name.toUpperCase() + " was not filled. /n");
    }
}
