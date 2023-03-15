package com.micaelops.livebrief2.account;

public class Account {

    /* Main Account data that is present in both types */
    private final AccountType accountType;
    private final String name, username;
    private final int age;


    public Account(String name, int age, AccountType type, String username) {
        this.name = name;
        this.age = age;
        this.accountType = type;
        this.username = username;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public enum AccountType {
        PARENT, CHILD
    }
}
