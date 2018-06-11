package com.dmedeiros.mybill.bill.service.throwablemanager;

import com.dmedeiros.mybill.bill.exception.PaidEmptyException;
import com.dmedeiros.mybill.bill.model.Paid;
import org.springframework.stereotype.Service;

@Service
public class PaidServiceThrowableManger implements ServiceThrowableManager<Paid> {


    @Override
    public void check(Paid paid) throws PaidEmptyException {
        if (paid.isEmpty())
            throw new PaidEmptyException();
    }

}
