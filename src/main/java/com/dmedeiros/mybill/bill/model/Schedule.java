package com.dmedeiros.mybill.bill.model;

import com.dmedeiros.mybill.util.Verification;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Schedule extends Bill {


    private int dayToPay;
    private boolean isMonthly;


    @JsonIgnore
    public boolean isEmpty() {
        if (Verification.isNullOrEmpty(this.name)
                || this.price == 0.0
                || this.dayToPay == 0
                || this.billType == null)
            return true;

        return false;
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


}
