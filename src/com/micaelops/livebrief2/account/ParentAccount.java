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
        this.children = new String[10]; // Max children is 10
    }

    public void addChild(String child){
        for(int i = 0; i < children.length; i++){
            if(children[i] == null || children[i].isEmpty()) {
                children[i] = child;
                return;
            }
        }
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
        String[] children = new String[this.children.length];
        System.arraycopy(data, 5, children, 0, children.length);
        return new ParentAccount(data[2], data[3], data[1], Integer.parseInt(data[4]), children);
    }

    @Override
    public String[] writeToStringArray() {
        String[] data = new String[14]; // 5 from account variables + accountType and 10 from children
        data[0] = "parent";
        data[1] = getName();
        data[2] = getUsername();
        data[3] = getPassword();
        data[4] = ""+getAge();
        System.arraycopy(children, 0, data, 5, children.length);
        return data;
    }
}
