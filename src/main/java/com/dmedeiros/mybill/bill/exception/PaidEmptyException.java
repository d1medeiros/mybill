package com.dmedeiros.mybill.bill.exception;

import com.dmedeiros.mybill.bill.model.Paid;

public class PaidEmptyException extends BillEmptyException {
    public PaidEmptyException() {
        super(Paid.class.getCanonicalName());
    }
}
