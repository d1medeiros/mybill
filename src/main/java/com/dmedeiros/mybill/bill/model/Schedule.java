package com.dmedeiros.mybill.bill.model;

import com.dmedeiros.mybill.util.Verification;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private int dayToPay;
    private boolean isMonthly;
    @Enumerated(EnumType.STRING)
    private BillType billType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Wallet wallet;

    @JsonIgnore
    public boolean isEmpty() {
        if (Verification.isNullOrEmpty(this.name)
                || this.price == 0.0
                || this.dayToPay == 0
                || this.billType == null)
            return true;

        return false;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getDayToPay() {
        return dayToPay;
    }
    public void setDayToPay(int dayToPay) {
        this.dayToPay = dayToPay;
    }
    public boolean isMonthly() {
        return isMonthly;
    }
    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }
    public BillType getBillType() {
        return billType;
    }
    public void setBillType(BillType billType) {
        this.billType = billType;
    }
    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


    @Override
    public String toString() {
        return String.format("%s - ID: %d nome: %s %s", this.getClass().getCanonicalName(), this.id, this.name, this.wallet);
    }

}
