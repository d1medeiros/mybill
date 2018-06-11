package com.dmedeiros.mybill.bill.model;

import com.dmedeiros.mybill.bill.exception.ScheduleEmptyException;
import com.dmedeiros.mybill.util.Verification;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Paid extends Bill{

    private LocalDate payday;


    public Paid() {
    }

    public Paid(Schedule schedule, LocalDate payday) {

        if (schedule.isAllSuperFieldsEmpty())
            throw new ScheduleEmptyException();

        this.name = schedule.getName();
        this.price = schedule.getPrice();
        this.billType = schedule.getBillType();
        this.wallet = schedule.getWallet();
        this.payday = payday;
    }

    @JsonIgnore
    public boolean isEmpty() {

        if (Verification.isNullOrEmpty(this.name)
                || this.price == 0.0
                || this.payday == null
                || this.billType == null)
            return true;

        return false;
    }

    public LocalDate getPayday() {
        return payday;
    }
    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }


}
