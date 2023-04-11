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

    /**
     * Adds child to Parent list
     * @param child username of the child
     */

    public void addChild(String child){

        // Tries to find a space to put the child username

        for(int i = 0; i < children.length; i++){
            if(children[i] == null || children[i].equalsIgnoreCase("null")) {
                children[i] = child;
                return;
            }
        }
    }

    /**
     * Check if there is any child on the list
     * @return true or false
     */

    public boolean hasChildren() {
        return children[0] !=null && !children[0].isEmpty() && !children[0].equalsIgnoreCase("null");
    }

    /**
     * Check if child username is present on the list
     * @param child username of the child
     * @return true or false
     */

    public boolean hasChild(String child) {
        return Arrays.stream(children).anyMatch(streamchild -> streamchild.equalsIgnoreCase(child));
    }

    /**
     * Get all children
     * @return String array
     */
    public String[] getChildren() {
        return children;
    }

    /**
     * Translates Data stored in the String array file into
     * an ParentAccount object
     * @param data array containing data
     * @return ParentAccount object
     */

    @Override
    public Account readFromStringArray(String[] data) {
        String[] children = new String[this.children.length];
        System.arraycopy(data, 5, children, 0, children.length);
        return new ParentAccount(data[2], data[3], data[1], Integer.parseInt(data[4]), children);
    }


    /**
     * Translates the Data in the Account object
     * into a String Array
     * @return data in string array form
     */

    @Override
    public String[] writeToStringArray() {
        String[] data = new String[15]; // 5 from account variables + accountType and 10 from children
        data[0] = "parent";
        data[1] = getName();
        data[2] = getUsername();
        data[3] = getPassword();
        data[4] = ""+getAge();
        System.arraycopy(children, 0, data, 5, children.length);
        return data;
    }
}
