package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.MyBillConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BillAndWalletServiceThrowableManager {

    @Autowired
    private PaidServiceThrowableManger paidServiceThrowableManger;
    @Autowired
    private ScheduleServiceThrowableManager scheduleServiceThrowableManager;
    @Autowired
    private WalletServiceThrowableManager walletServiceThrowableManager;


    protected void check(Wallet wallet, Paid paid) {
        walletServiceThrowableManager.check(wallet);
        paidServiceThrowableManger.check(paid);
    }

    protected void check(Wallet wallet, Schedule schedule) {
        walletServiceThrowableManager.check(wallet);
        scheduleServiceThrowableManager.check(schedule);
    }

    protected void check(Wallet wallet, Long value) {
        walletServiceThrowableManager.check(wallet);
        if (value <= MyBillConstants.INVALID_LONG)
            throw new IllegalArgumentException(String.format("illegal value: %s", value));
    }

    protected void check(Wallet wallet, int value) {
        walletServiceThrowableManager.check(wallet);
        if (value <= MyBillConstants.INVALID_INT)
            throw new IllegalArgumentException(String.format("illegal value: %d", value));
    }

    protected void check(Wallet wallet, String value) {
        walletServiceThrowableManager.check(wallet);
        Assert.hasText(value, String.format("illegal value: %d", value));
    }

    protected void check(Wallet wallet) {
        walletServiceThrowableManager.check(wallet);
    }



}
