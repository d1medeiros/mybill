package com.dmedeiros.mybill.bill.model;

import com.dmedeiros.mybill.util.Verification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private LocalDate payday;
    private boolean isPaid;
    private int dayToPay;
    @Enumerated(EnumType.STRING)
    private BillType billType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Wallet wallet;

    @JsonIgnore
    public boolean isEmpty() {
//        Assert.hasText(this.name);

        if (Verification.isNullOrEmpty(this.name)
                || this.price == 0.0
                || this.billType == null)
            return true;

        return false;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getDayToPay() {
        return dayToPay;
    }

    public void setDayToPay(int dayToPay) {
        this.dayToPay = dayToPay;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }


    @Override
    public String toString() {
        return String.format("ID: %d nome: %s %s", this.id, this.name, this.wallet);
    }


}
