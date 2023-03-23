package com.micaelops.livebrief2.account;

public class Account {

    // Name of the user and username
    private final String name, username;

    // Age of the user
    private int age;


    public Account(String name, int age, String username) {
        this.name = name;
        this.age = age;
        this.username = username;
    }

    public String getUsername() { return username; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getName() { return name; }

}
