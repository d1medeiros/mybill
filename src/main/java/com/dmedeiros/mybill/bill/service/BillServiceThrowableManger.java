package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.BillEmptyException;
import com.dmedeiros.mybill.bill.model.Bill;
import org.springframework.stereotype.Service;

@Service
public class BillServiceThrowableManger implements ServiceThrowableManager<Bill> {


    @Override
    public void check(Bill bill) throws BillEmptyException {
        if (bill.isEmpty())
            throw new BillEmptyException();
    }

}
