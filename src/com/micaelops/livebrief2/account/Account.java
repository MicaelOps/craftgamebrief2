package com.micaelops.livebrief2.account;

 public abstract class Account {

    // Name of the user and username
    private final String name, username;

    private String password;

    // Age of the user
    private int age;

    public Account(){
        this.username = "";
        this.password = "";
        this.name = "";
        this.age = 0;
    }

    public Account(String username, String password, String name, int age) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract Account readFromStringArray(String[] data);

    public abstract String writeToString();

 }
