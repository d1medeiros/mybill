package com.dmedeiros.mybill.bill.exception;

import com.dmedeiros.mybill.bill.model.BillType;

public class PaymentException extends RuntimeException {

    public PaymentException() {
        super("Paid was not filled.");
    }
    public PaymentException(BillType billType) {
        super("Cant proceed with the payment because of the bill type: " + billType.name());
    }

}
