package com.dmedeiros.mybill.bill.repository;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaidRepository extends CrudRepository<Paid, Long> {

    Paid findByIdAndWallet(Long billId, Wallet wallet);

    Paid findByNameAndWallet(String name, Wallet wallet);

    List<Paid> findByPaydayAndWallet(LocalDate payday, Wallet wallet);

    @Query("select p from Paid p where month(p.payday) = ?2 and p.wallet = ?1")
    List<Paid> findByPaydayMonthAndWallet(Wallet wallet, int month);

    @Query("select p from Paid p where year(p.payday) = ?2 and p.wallet = ?1")
    List<Paid> findByPaydayYearAndWallet(Wallet wallet, int year);

    List<Paid> findByWallet(Wallet wallet);
}
