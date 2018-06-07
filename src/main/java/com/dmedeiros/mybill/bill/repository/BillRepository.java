package com.dmedeiros.mybill.bill.repository;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {

    Bill findByIdAndWallet(Long billId, Wallet wallet);

    Bill findByNameAndWallet(String name, Wallet wallet);

    List<Bill> findByPaydayAndWallet(LocalDate payday, Wallet wallet);

    @Query("select b from Bill b where month(b.payday) = ?2 and b.wallet = ?1")
    List<Bill> findByPaydayMonthAndWallet(Wallet wallet, int month);

    @Query("select b from Bill b where year(b.payday) = ?2 and b.wallet = ?1")
    List<Bill> findByPaydayYearAndWallet(Wallet wallet, int year);

    List<Bill> findByIsPaidAndWallet(boolean isPaid, Wallet wallet);
}
