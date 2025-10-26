package com.dinesh.springaopdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double balance;
    @Column(name = "active")
    private boolean active;

    public Account() {
    }

    public Account(Long id, String name, double balance,boolean active) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.active=active;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name  +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }

}
