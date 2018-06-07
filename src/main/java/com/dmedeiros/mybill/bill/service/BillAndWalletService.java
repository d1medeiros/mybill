package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.BillRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import com.dmedeiros.mybill.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillAndWalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BillRepository billRepository;


    public void save(Wallet wallet, Bill bill) {
        bill.setWallet(wallet);
        Bill savedBill = billRepository.save(bill);
    }


    public void selectById(User user, int billId) {

    }
}
