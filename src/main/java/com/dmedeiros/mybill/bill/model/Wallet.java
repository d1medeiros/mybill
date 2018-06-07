package com.dmedeiros.mybill.bill.model;


import com.dmedeiros.mybill.user.model.User;

import javax.persistence.*;
import java.util.*;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy="wallet")
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Bill> bills = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Bill> schedules = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public void setBill(Bill bill) {
        this.bills.add(bill);
    }

    public Set<Bill> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Bill> schedules) {
        this.schedules = schedules;
    }

    public void setSchedule(Bill bill) {
        this.schedules.add(bill);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) &&
                Objects.equals(user, wallet.user) &&
                Objects.equals(bills, wallet.bills) &&
                Objects.equals(schedules, wallet.schedules);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, bills, schedules);
    }
}
