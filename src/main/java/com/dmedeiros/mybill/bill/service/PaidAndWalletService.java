package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Schedule;
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


    public Paid save(Wallet wallet, Paid paid) {
        check(wallet, paid);
        paid.setWallet(wallet);
        return billRepository.save(paid);
    }

    public List<Paid> selectAll(Wallet wallet) {
        check(wallet);
        return billRepository.findByWallet(wallet);
    }

    public Paid selectById(Wallet wallet, Long billId) {
        check(wallet, billId);
        Paid paid = billRepository.findByIdAndWallet(billId, wallet);
        return paid;
    }

    public Paid selectByName(Wallet wallet, String name) {
        check(wallet, name);
        Paid paid = billRepository.findByNameAndWallet(name, wallet);
        return paid;
    }

    public List<Paid> selectByDate(Wallet wallet, LocalDate payday) {
        check(wallet);
        List<Paid> paids = billRepository.findByPaydayAndWallet(payday, wallet);
        return paids;
    }

    public List<Paid> selectByMonth(Wallet wallet, int month) {
        check(wallet, month);
        List<Paid> paids = billRepository.findByPaydayMonthAndWallet(wallet, month);
        return paids;
    }

    public List<Paid> selectByYear(Wallet wallet, int year) {
        check(wallet, year);
        List<Paid> paids = billRepository.findByPaydayYearAndWallet(wallet, year);
        return paids;
    }

    public void remove(Wallet wallet, Long id) {
        check(wallet, id);
        billRepository.deleteById(id);
    }

    @Deprecated
    public void update(Wallet wallet, Paid paid) {
        check(wallet, paid);
        Paid paidFounded = billRepository.findByIdAndWallet(paid.getId(), wallet);
        paidFounded.setName(paid.getName());
        paidFounded.setPrice(paid.getPrice());
        paidFounded.setPayday(paid.getPayday());
        paidFounded.setBillType(paid.getBillType());
        billRepository.save(paidFounded);
    }

    public void generateBillFromSchedule(Schedule schedule) {
        Paid paid = new Paid();
        paid.setName(schedule.getName());
        paid.setPrice(schedule.getPrice());
        paid.setBillType(schedule.getBillType());
        paid.setWallet(schedule.getWallet());
    }
}
