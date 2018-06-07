package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.exception.BillEmptyException;
import com.dmedeiros.mybill.bill.exception.WalletEmptyException;
import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.BillRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import com.dmedeiros.mybill.util.MyBillConstants;
import com.dmedeiros.mybill.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillAndWalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BillRepository billRepository;


    public void save(Wallet wallet, Bill bill) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();

        if (bill.isEmpty())
            throw new BillEmptyException();

        bill.setWallet(wallet);
        Bill savedBill = billRepository.save(bill);
    }


    public Bill selectById(Wallet wallet, Long billId) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();
        if (billId <= MyBillConstants.INVALID_ID)
            throw new IllegalArgumentException(billId.toString());

        Bill bill = billRepository.findByIdAndWallet(billId, wallet);
        return bill;
    }

    public Bill selectByName(Wallet wallet, String name) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();
        if (Verification.isNullOrEmpty(name))
            throw new IllegalArgumentException(name);

        Bill bill = billRepository.findByNameAndWallet(name, wallet);
        return bill;
    }

    public List<Bill> selectByDate(Wallet wallet, LocalDate payday) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();

        List<Bill> bills = billRepository.findByPaydayAndWallet(payday, wallet);
        return bills;
    }

    public List<Bill> selectByMonth(Wallet wallet, int month) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();
        if (month == MyBillConstants.INVALID_MONTH)
            throw new IllegalArgumentException();

        List<Bill> bills = billRepository.findByPaydayMonthAndWallet(wallet, month);
        return bills;
    }

    public List<Bill> selectByYear(Wallet wallet, int year) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();
        if (year == MyBillConstants.INVALID_YEAR)
            throw new IllegalArgumentException();

        List<Bill> bills = billRepository.findByPaydayYearAndWallet(wallet, year);
        return bills;
    }

    public List<Bill> findByPaidBill(Wallet wallet) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();

        List<Bill> bills = billRepository.findByIsPaidAndWallet(true, wallet);
        return bills;
    }

    public List<Bill> findByScheduleBill(Wallet wallet) {
        if (wallet.isEmpty())
            throw new WalletEmptyException();

        List<Bill> bills = billRepository.findByIsPaidAndWallet(false, wallet);
        return bills;
    }
}
