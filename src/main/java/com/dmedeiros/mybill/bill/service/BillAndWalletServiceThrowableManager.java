package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.BillEmptyException;
import com.dmedeiros.mybill.bill.exception.WalletEmptyException;
import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.MyBillConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BillAndWalletServiceThrowableManager {

    @Autowired
    private BillServiceThrowableManger billServiceThrowableManger;
    @Autowired
    private WalletServiceThrowableManager walletServiceThrowableManager;


    protected void check(Wallet wallet, Bill bill) throws WalletEmptyException, BillEmptyException {
        walletServiceThrowableManager.check(wallet);
        billServiceThrowableManger.check(bill);
    }

    protected void check(Wallet wallet, Long value) throws WalletEmptyException {
        walletServiceThrowableManager.check(wallet);
        if (value <= MyBillConstants.INVALID_LONG)
            throw new IllegalArgumentException(String.format("illegal value: %s", value));
    }

    protected void check(Wallet wallet, int value) throws WalletEmptyException {
        walletServiceThrowableManager.check(wallet);
        if (value <= MyBillConstants.INVALID_INT)
            throw new IllegalArgumentException(String.format("illegal value: %d", value));
    }

    protected void check(Wallet wallet, String value) throws WalletEmptyException {
        walletServiceThrowableManager.check(wallet);
        Assert.hasText(value, String.format("illegal value: %d", value));
    }

    protected void check(Wallet wallet) throws WalletEmptyException {
        walletServiceThrowableManager.check(wallet);
    }



}
