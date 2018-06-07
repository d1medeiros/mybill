package com.dmedeiros.mybill.bill.repository;

import com.dmedeiros.mybill.bill.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
