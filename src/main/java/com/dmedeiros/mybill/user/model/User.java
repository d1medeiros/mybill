package com.dmedeiros.mybill.user.model;

import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.util.VerificationUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "EMP_SEQ", allocationSize = 25, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    private Long id;
    private String name;
    private boolean active;
    private LocalDate lastAccess;
    private String password;
    @Column(unique = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastAccess, user.lastAccess) &&
                Objects.equals(password, user.password) &&
                Objects.equals(login, user.login) &&
                Objects.equals(wallet, user.wallet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, active, lastAccess, password, login, wallet);
    }


    @Override
    public String toString() {
        return String.format("ID: %d Nome: %s", this.id, this.name);
    }

    public boolean isEmpty(User user) {
        if (VerificationUtil.isNullOrEmpty(this.getName())
                || VerificationUtil.isNullOrEmpty(this.getLogin())
                || VerificationUtil.isNullOrEmpty(this.getPassword()))
            return true;
        return false;
    }
}
