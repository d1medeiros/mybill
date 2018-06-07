package com.dmedeiros.mybill.bill.model;

import java.time.LocalDate;

public class BillFactory {

    public static Bill clone(Bill bill) {
        Bill newBill = new Bill();
        newBill.setName(bill.getName());
        newBill.setPrice(bill.getPrice());
        newBill.setWallet(bill.getWallet());
        newBill.setPaid(bill.isPaid());
        newBill.setPayday(bill.getPayday());
        newBill.setDayToPay(bill.getDayToPay());
        newBill.setBillType(bill.getBillType());
        return newBill;
    }

    public static void changeBillTypeOnPayment(Bill bill) {
        BillAction action = bill.getBillType().getAction();
        BillType billType = BillType.buildBillType(BillGroup.NORMAL, action);
        bill.setBillType(billType);
    }

    public static void preparePayment(Bill clone) {
        clone.setDayToPay(0);
        clone.setPayday(LocalDate.now());
        clone.setPaid(true);
        BillFactory.changeBillTypeOnPayment(clone);
    }
}
