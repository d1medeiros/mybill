package com.dmedeiros.mybill.bill.repository;

import com.dmedeiros.mybill.bill.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
