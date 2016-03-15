package com.web;

import java.io.Serializable;

/**
 * Created by devendra on 3/12/2016.
 */
public class MyAccount implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private int balance;
    private String bankName;

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getBalance()
    {
        return balance;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }
}
