package com.dmedeiros.mybill.bill.model;


import com.dmedeiros.mybill.user.model.User;

import javax.persistence.*;

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



}
