package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.BillEmptyException;
import com.dmedeiros.mybill.bill.model.Paid;
import org.springframework.stereotype.Service;

@Service
public class BillServiceThrowableManger implements ServiceThrowableManager<Paid> {


    @Override
    public void check(Paid paid) throws BillEmptyException {
        if (paid.isEmpty())
            throw new BillEmptyException();
    }

}
