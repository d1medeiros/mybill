package com.dmedeiros.mybill.bill.model;


import com.dmedeiros.mybill.user.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;



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

    public boolean isEmpty(){
        if (this.user == null)
            return true;

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) &&
                Objects.equals(user, wallet.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user);
    }
}
