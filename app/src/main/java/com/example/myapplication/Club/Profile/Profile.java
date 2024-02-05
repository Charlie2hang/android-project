package com.example.myapplication.Club.Profile;

import java.io.Serializable;

public class Profile implements Serializable {

    private String link;
    private String name, accountName;
    private String phoneNumber;

    public Profile() {
    }

    public Profile(String link, String name, String accountName, String phoneNumber) {
        this.link = link;
        this.name = name;
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
