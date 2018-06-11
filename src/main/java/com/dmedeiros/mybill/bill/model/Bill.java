package com.dmedeiros.mybill.bill.model;

import com.dmedeiros.mybill.util.Verification;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@MappedSuperclass
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected Double price;
    @Enumerated(EnumType.STRING)
    protected BillType billType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    protected Wallet wallet;

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


    @JsonIgnore
    public boolean isAllSuperFieldsEmpty() {
        if (Verification.isNullOrEmpty(this.name)
                || this.price == 0.0
                || this.wallet.isEmpty()
                || this.billType == null)
            return true;

        return false;
    }

}
