package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.BillEmptyException;
import com.dmedeiros.mybill.bill.exception.PaymentException;
import com.dmedeiros.mybill.bill.exception.WalletEmptyException;
import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.BillGroup;
import com.dmedeiros.mybill.bill.model.BillType;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.MyBillConstants;
import com.dmedeiros.mybill.util.Verification;

public class BillAndWalletServiceThrowableManager {



    protected void check(Wallet wallet, Bill bill) throws WalletEmptyException, BillEmptyException{
        checkWallet(wallet);
        if (bill.isEmpty())
            throw new BillEmptyException();
    }

    protected void checkToPay(Wallet wallet, Bill bill) throws WalletEmptyException, BillEmptyException, PaymentException{
        checkWallet(wallet);

        if (bill.isEmpty())
            throw new BillEmptyException();

        if (bill.getBillType().isInGroup(BillGroup.NORMAL))
            throw new PaymentException(bill.getBillType());

    }

    protected void check(Wallet wallet, Long value) throws WalletEmptyException, BillEmptyException{
        checkWallet(wallet);
        if (value <= MyBillConstants.INVALID_LONG)
            throw new IllegalArgumentException(String.format("illegal value: %s", value));
    }

    protected void check(Wallet wallet, int value) throws WalletEmptyException, BillEmptyException{
        checkWallet(wallet);
        if (value <= MyBillConstants.INVALID_INT)
            throw new IllegalArgumentException(String.format("illegal value: %d", value));
    }

    protected void check(Wallet wallet, String value) throws WalletEmptyException, BillEmptyException{
        checkWallet(wallet);
        if (Verification.isNullOrEmpty(value))
            throw new IllegalArgumentException(String.format("illegal value: %s", value));
    }

    protected void checkWallet(Wallet wallet) throws WalletEmptyException{
        if (wallet.isEmpty())
            throw new WalletEmptyException();
    }


}
