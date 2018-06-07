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
public class BillAndWalletService extends BillAndWalletServiceThrowableManager{

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BillRepository billRepository;


    public void save(Wallet wallet, Bill bill) {
        check(wallet, bill);
        bill.setWallet(wallet);
        Bill savedBill = billRepository.save(bill);
    }

    public Bill selectById(Wallet wallet, Long billId) {
        check(wallet, billId);
        Bill bill = billRepository.findByIdAndWallet(billId, wallet);
        return bill;
    }

    public Bill selectByName(Wallet wallet, String name) {
        check(wallet, name);
        Bill bill = billRepository.findByNameAndWallet(name, wallet);
        return bill;
    }

    public List<Bill> selectByDate(Wallet wallet, LocalDate payday) {
        checkWallet(wallet);
        List<Bill> bills = billRepository.findByPaydayAndWallet(payday, wallet);
        return bills;
    }

    public List<Bill> selectByMonth(Wallet wallet, int month) {
        check(wallet, month);
        List<Bill> bills = billRepository.findByPaydayMonthAndWallet(wallet, month);
        return bills;
    }

    public List<Bill> selectByYear(Wallet wallet, int year) {
        check(wallet, year);
        List<Bill> bills = billRepository.findByPaydayYearAndWallet(wallet, year);
        return bills;
    }

    public List<Bill> findByPaidBill(Wallet wallet) {
        checkWallet(wallet);
        List<Bill> bills = billRepository.findByIsPaidAndWallet(true, wallet);
        return bills;
    }

    public List<Bill> findByScheduleBill(Wallet wallet) {
        checkWallet(wallet);
        List<Bill> bills = billRepository.findByIsPaidAndWallet(false, wallet);
        return bills;
    }
}
