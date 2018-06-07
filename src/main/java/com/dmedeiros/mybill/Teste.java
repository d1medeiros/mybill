package com.dmedeiros.mybill;

import com.dmedeiros.mybill.bill.model.BillType;
import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.bill.service.BillAndWalletServiceThrowableManager;

import java.util.Optional;

public class Teste {

    public static void main(String[] args) {

        System.out.println(BillGroup.PLANEJADO);


    }
}

enum BillGroup {

    PLANEJADO(BillType.GANHO_PLANEJADO, BillType.GASTOS_PLANEJADO),
    NORMAL(BillType.GANHO_NORMAL, BillType.GASTOS_NORMAL);


    private BillType[] types;

    BillGroup(BillType... types) {
        this.types = types;
    }


}