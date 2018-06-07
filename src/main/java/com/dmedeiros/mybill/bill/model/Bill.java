package com.dmedeiros.mybill.bill.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private LocalDate payday = LocalDate.now();
    private boolean isPaid;
    private int dayToPay;
    @Enumerated(EnumType.STRING)
    private BillType billType;


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return isPaid == bill.isPaid &&
                dayToPay == bill.dayToPay &&
                Objects.equals(id, bill.id) &&
                Objects.equals(name, bill.name) &&
                Objects.equals(price, bill.price) &&
                Objects.equals(payday, bill.payday) &&
                billType == bill.billType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, payday, isPaid, dayToPay, billType);
    }
}
