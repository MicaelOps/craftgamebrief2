package com.micaelops.livebrief2.account;

import java.util.Arrays;

public class ParentAccount extends Account{

    // Holds an array of children usernames.
    private final String[] children;

    public ParentAccount(String username, String password, String name, int age, String[] children) {
        super(username, password, name, age);
        this.children = children;
    }

    public ParentAccount() {
        this.children = new String[1];
    }

    public void addChild(String child){

    }

    public boolean hasChildren() {
        return children[0] !=null && !children[0].isEmpty();
    }
    public boolean hasChild(String child) {
        return Arrays.stream(children).anyMatch(streamChild -> streamChild.equalsIgnoreCase(child));
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
