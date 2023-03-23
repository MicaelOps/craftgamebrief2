package com.micaelops.livebrief2.account;

public class Account {

    /* Main Account data that is present in both types */
    private final String name, username;

    private int age;


    public Account(String name, int age, String username) {
        this.name = name;
        this.age = age;
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

}
