package com.micaelops.livebrief2.account;

public class ParentAccount extends Account{

    // Holds an array of children usernames.
    private final String[] children;

    public ParentAccount(String name, String username, int age, String[] children) {
        super(name, age, username);
        this.children = children;
    }

    public String[] getChildrenUsernames() {
        return children;
    }
}
