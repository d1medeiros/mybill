package com.dmedeiros.mybill;

import com.dmedeiros.mybill.bill.model.Wallet;

import java.util.Optional;

public class Teste {

    public static void main(String[] args) {


        Wallet wallet = new Wallet();

//        wallet.setId(55l);

        boolean present = Optional.of(wallet).isPresent();
        System.out.println(present);


    }
}
