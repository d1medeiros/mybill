package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.BillRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillAndWalletService extends BillAndWalletServiceThrowableManager {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BillRepository billRepository;


    public Bill save(Wallet wallet, Bill bill) {
        check(wallet, bill);
        bill.setWallet(wallet);
        return billRepository.save(bill);
    }

    public List<Bill> selectAll(Wallet wallet) {
        check(wallet);
        return billRepository.findByWallet(wallet);
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
        check(wallet);
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

    public void remove(Wallet wallet, Long id) {
        check(wallet, id);
        billRepository.deleteById(id);
    }

    @Deprecated
    public void update(Wallet wallet, Bill bill) {
        check(wallet, bill);
        Bill billFounded = billRepository.findByIdAndWallet(bill.getId(), wallet);
        billFounded.setName(bill.getName());
        billFounded.setPrice(bill.getPrice());
        billFounded.setPayday(bill.getPayday());
        billFounded.setBillType(bill.getBillType());
        billRepository.save(billFounded);
    }

}
