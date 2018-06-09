package com.dmedeiros.mybill.user.model;

import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.Verification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "EMP_SEQ", allocationSize = 25, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    private Long id;
    private String name;
    private boolean active;
    private LocalDate lastAccess;
    @NotNull
    private String password;
    @NotNull @Column(unique = true)
    private String login;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private Wallet wallet;


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDate lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }




    @Override
    public String toString() {
        return String.format("ID: %d Nome: %s, Login: %s Password: %s ", this.id, this.name, this.login, this.password);
    }

    public boolean isEmpty() {
        if (Verification.isNullOrEmpty(this.getLogin())
                || Verification.isNullOrEmpty(this.getPassword()))
            return true;
        return false;
    }
}
