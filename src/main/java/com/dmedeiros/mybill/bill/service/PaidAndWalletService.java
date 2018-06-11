package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.PaidRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaidAndWalletService extends BillAndWalletServiceThrowableManager {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PaidRepository paidRepository;


    public Paid save(Wallet wallet, Paid paid) {
        check(wallet, paid);
        paid.setWallet(wallet);
        return paidRepository.save(paid);
    }

    public List<Paid> selectAll(Wallet wallet) {
        check(wallet);
        return paidRepository.findByWallet(wallet);
    }

    public Paid selectById(Wallet wallet, Long billId) {
        check(wallet, billId);
        Paid paid = paidRepository.findByIdAndWallet(billId, wallet);
        return paid;
    }

    public Paid selectByName(Wallet wallet, String name) {
        check(wallet, name);
        Paid paid = paidRepository.findByNameAndWallet(name, wallet);
        return paid;
    }

    public List<Paid> selectByDate(Wallet wallet, LocalDate payday) {
        check(wallet);
        List<Paid> paids = paidRepository.findByPaydayAndWallet(payday, wallet);
        return paids;
    }

    public List<Paid> selectByMonth(Wallet wallet, int month) {
        check(wallet, month);
        List<Paid> paids = paidRepository.findByPaydayMonthAndWallet(wallet, month);
        return paids;
    }

    public List<Paid> selectByYear(Wallet wallet, int year) {
        check(wallet, year);
        List<Paid> paids = paidRepository.findByPaydayYearAndWallet(wallet, year);
        return paids;
    }

    public void remove(Wallet wallet, Long id) {
        check(wallet, id);
        paidRepository.deleteById(id);
    }

    @Deprecated
    public void update(Wallet wallet, Paid paid) {
        check(wallet, paid);
        Paid paidFounded = paidRepository.findByIdAndWallet(paid.getId(), wallet);
        paidFounded.setName(paid.getName());
        paidFounded.setPrice(paid.getPrice());
        paidFounded.setPayday(paid.getPayday());
        paidFounded.setBillType(paid.getBillType());
        paidRepository.save(paidFounded);
    }

    public Paid generatePaidFromSchedule(Schedule schedule) {
        return new Paid(schedule, LocalDate.now());
    }
}
