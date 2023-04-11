package com.micaelops.livebrief2.account;

/**
 * Abstract class of the Account
 * This class will only contain the data that is required by default
 *
 * Any extension of this class of will have its own unique variables and methods
 * of saving and loading data from database.
 */
 public abstract class Account {

    // Name of the user and username
    private final String name, username;

    // Age of the user (changeable but not by design)
    private final int age;

    // Password (changeable)
    private String password;


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
    /**
     * Gets the name of the account
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the username of the account
     * @return username
     */
    public String getUsername() {
        return username;
    }

     /**
      * Gets the Password of the account
      * @return password
      */

    public String getPassword() {
        return password;
    }

    /**
     * Gets the age of the user
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the password
     * @param password new password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Translates Data stored in the String array file into
     * an Account object
     * @param data array containing data
     * @return Account object
     */

    public abstract Account readFromStringArray(String[] data);

    /**
     * Translates the Data in the Account object
     * into a String Array
     * @return data in string array form
     */

    public abstract String[] writeToStringArray();
}
