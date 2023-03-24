package com.micaelops.livebrief2.account;

public class ParentAccount extends Account{

    // Holds an array of children usernames.
    private final String[] children;

    public ParentAccount(String username, String password, String name, int age, String[] children) {
        super(username, password, name, age);
        this.children = children;
    }

    public ParentAccount() {
        this.children = null;
    }

    public String[] getChildren() {
        return children;
    }

    @Override
    public Account readFromStringArray(String[] data) {
        return null;
    }

    @Override
    public String writeToString() {
        return null;
    }
}
