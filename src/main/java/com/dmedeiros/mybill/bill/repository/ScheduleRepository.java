package com.dmedeiros.mybill.bill.repository;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Schedule findByIdAndWallet(Long scheduleId, Wallet wallet);

    Schedule findByNameAndWallet(String name, Wallet wallet);

    List<Schedule> findByDayToPayAndWallet(int dayToPay, Wallet wallet);
}
