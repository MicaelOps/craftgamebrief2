package com.micaelops.livebrief2.database;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

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


    private final HashMap<String, Account> accounts;
    private final File accountsFile;


    // Singleton design
    private static Database instance;


    public Database() {
        accountsFile = new File("accounts.data");
        accounts = new HashMap<>();
    }


    public static Database getInstance() {

        if(instance == null)
            instance = new Database();

        return instance;
    }


    /***
     * Every line represents an account
     * Format expected:
     * accounttype:
     * @return
     */
    public boolean loadAccounts(){

        if(!accountsFile.exists())
            return true;

        try {
            for(String accountData : Files.readAllLines(accountsFile.toPath())) {
                String[] data = accountData.split(":");

                if(data.length > 0){

                    Account account = null;

                    if(data[0].equalsIgnoreCase("child"))
                        account = new ChildAccount().readFromStringArray(data);
                    else if(data[0].equalsIgnoreCase("parent"))
                        account = new ParentAccount().readFromStringArray(data);

                    if(account == null){
                        System.out.println("Error: Invalid data detected!");
                        return false;
                    }
                    accounts.put(account.getUsername(), account);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    public boolean saveData() {

        try (BufferedWriter outputStream = Files.newBufferedWriter(accountsFile.toPath())) {

            for(Account account : accounts.values()) {
                outputStream.write(account.writeToString());
                outputStream.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    public boolean checkPassword(String user, String password){

        Account account = getAccount(user);

        if(account == null)
            return false;

        return account.getPassword().equals(password);
    }

    public Account getAccount(String username) {

        if(accounts.containsKey(username))
            return accounts.get(username);

        return null;
    }


}
