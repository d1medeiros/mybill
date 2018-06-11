package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.ScheduleRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import com.dmedeiros.mybill.bill.service.throwablemanager.BillAndWalletServiceThrowableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAndWalletService extends BillAndWalletServiceThrowableManager {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;


    public Schedule save(Wallet wallet, Schedule schedule) {
        check(wallet, schedule);
        schedule.setWallet(wallet);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> selectAll(Wallet wallet) {
        check(wallet);
        return scheduleRepository.findByWallet(wallet);
    }

    public Schedule selectById(Wallet wallet, Long scheduleId) {
        check(wallet, scheduleId);

        Schedule schedule = scheduleRepository.findByIdAndWallet(scheduleId, wallet);
        return schedule;
    }

    public Schedule selectByName(Wallet wallet, String name) {
        check(wallet, name);
        Schedule schedule = scheduleRepository.findByNameAndWallet(name, wallet);
        return schedule;
    }

    public List<Schedule> selectByDay(Wallet wallet, int day) {
        check(wallet, day);
        List<Schedule> schedules = scheduleRepository.findByDayToPayAndWallet(day, wallet);
        return schedules;
    }

    public void remove(Wallet wallet, Long id) {
        check(wallet, id);
        scheduleRepository.deleteById(id);
    }

    @Deprecated
    public void update(Wallet wallet, Schedule schedule) {
        check(wallet, schedule);
        Schedule scheduleFounded = scheduleRepository.findByIdAndWallet(schedule.getId(), wallet);
        scheduleFounded.setName(schedule.getName());
        scheduleFounded.setPrice(schedule.getPrice());
        scheduleFounded.setDayToPay(schedule.getDayToPay());
        scheduleFounded.setBillType(schedule.getBillType());
        scheduleRepository.save(scheduleFounded);
    }



//    public void payBill(Wallet wallet, Paid schedule) {
//        checkToPay(wallet, schedule);
//        Paid scheduleFounded = scheduleRepository.findByIdAndWallet(schedule.getId(), wallet);
//        Paid clone = BillFactory.clone(scheduleFounded);
//        BillFactory.preparePayment(clone);
//        scheduleRepository.save(clone);
//    }
}
