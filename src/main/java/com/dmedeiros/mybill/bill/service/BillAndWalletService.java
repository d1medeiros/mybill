package com.dmedeiros.mybill.bill.service;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.repository.BillRepository;
import com.dmedeiros.mybill.bill.repository.WalletRepository;
import com.dmedeiros.mybill.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillAndWalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BillRepository billRepository;


    public void saveBill(User user, Bill bill) {
        Bill savedBill = billRepository.save(bill);
        Wallet wallet = user.getWallet();
        wallet.setBill(savedBill);
        walletRepository.save(wallet);

    }

    public void saveSchedule(User user, Bill schedule) {
        Bill savedSchedule = billRepository.save(schedule);
        Wallet wallet = user.getWallet();
        wallet.setSchedule(savedSchedule);
        walletRepository.save(wallet);
    }
}
