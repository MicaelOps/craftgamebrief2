package com.micaelops.livebrief2.database;

import com.micaelops.livebrief2.account.Account;

import java.util.HashMap;

/***
 * Database system for the accounts and game.
 * We use the Json format to parse data
 *
 * To not overcomplicate the project we made a few assumptions:
 * We don't use database software like MySQL
 * We assume that there is only one computer that is able to play the game which is where all the data is stored
 * We assume that there is not going to be a lot of users to the point of overstoring the Hashmap
 */
public class Database {


    private final HashMap<String, Account> accounts = new HashMap<>();

    // Singleton design
    private static Database instance;


    public Database() {}

    public static Database getInstance() {

        if(instance == null)
            instance = new Database();

        return instance;
    }


    public boolean loadData(){
        return false;
    }

    public boolean checkPassword(String user, String password){
        return false;
    }

    public Account getAccount(String username) {
        return null;
    }
}
