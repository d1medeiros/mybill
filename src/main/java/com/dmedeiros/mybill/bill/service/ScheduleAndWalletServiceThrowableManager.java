package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.*;
import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.MyBillConstants;
import com.dmedeiros.mybill.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ScheduleAndWalletServiceThrowableManager {


    @Autowired
    private ScheduleServiceThrowableManager scheduleServiceThrowableManager;
    @Autowired
    private WalletServiceThrowableManager walletServiceThrowableManager;


    protected void check(Wallet wallet, Schedule schedule) throws WalletEmptyException, ScheduleEmptyException {
        walletServiceThrowableManager.check(wallet);
        scheduleServiceThrowableManager.check(schedule);
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
