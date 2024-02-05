package com.example.myapplication.entity;

import java.io.Serializable;

public class Account implements Serializable {

    private String name;
    private String password;
    private String email;
    private AccountRole role;

    public Account() {

    }

    public Account(String name, String password,String email, AccountRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){return email; }

    public void SetEmail(){this.email = email;}

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}