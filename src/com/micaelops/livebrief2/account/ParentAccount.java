package com.micaelops.livebrief2.account;

public class ParentAccount extends Account{

    private final String[] children;

    public ParentAccount(String name, String username, int age, AccountType type, String[] children) {
        super(name, age, AccountType.PARENT, username);
        this.children = children;
    }

    public String[] getChildren() {
        return children;
    }
}
