package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.WalletEmptyException;
import com.dmedeiros.mybill.bill.model.Wallet;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceThrowableManager implements ServiceThrowableManager<Wallet> {


    @Override
    public void check(Wallet wallet) throws WalletEmptyException {
        if (wallet.isEmpty())
            throw new WalletEmptyException();
    }

}
