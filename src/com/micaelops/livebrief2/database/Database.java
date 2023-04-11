package com.micaelops.livebrief2.database;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;

import java.io.*;
import java.nio.file.Files;

import java.util.Collection;
import java.util.HashMap;


/***
 * Database system for the accounts.
 *
 * By design, we did not specify how we would save data or what we would use hence
 * A rather shallow data processing using Strings (inspired from carsales)
 *
 * Data Format:
 * type_of_account:name:username:password:age:more_data
 *
 * More assumptions based on the lack of a complete design:
 *
 * We don't use database software like MySQL and therefore use text file format.
 *
 * We assume that there is only one computer that is able to play the game which
 * is where all the data is stored for parents and children
 *
 */
public class Database {

    // Holds all the accounts data
    private final HashMap<String, Account> accounts;

    // File holds the data
    private final File accountsFile;

    // Singleton design
    private static Database instance;


    public Database() {
        accountsFile = new File("accounts.data");
        accounts = new HashMap<>();
    }

    // Singleton Design
    public static Database getInstance() {

        if(instance == null)
            instance = new Database();

        return instance;
    }

    /**
     * Loads All accounts from file
     * @return success or not
     */

    public boolean loadAccounts(){

        // Checks if the file exists
        if(!accountsFile.exists())
            return true;

        try {

            // Each Account data is separated by new line
            for(String accountData : Files.readAllLines(accountsFile.toPath())) {

                String[] data = accountData.split(":");

                if(data.length > 0){

                    Account account = null;

                    // Every Account extension has it own way of reading and writing

                    if(data[0].equalsIgnoreCase("child"))
                        account = new ChildAccount().readFromStringArray(data);
                    else if(data[0].equalsIgnoreCase("parent"))
                        account = new ParentAccount().readFromStringArray(data);

                    // Skips to the next line of data if current is corrupted
                    if(account == null){
                        System.out.println("Error: Invalid data detected! Data: "+accountData);
                        continue;
                    }

                    // Adds account
                    accounts.put(account.getUsername(), account);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: Invalid data detected! Stopping loading... ");
            System.out.println("Info "+e.getMessage());
        }
        return false;
    }

    /**
     * Saves Data of All Accounts
     */

    public void saveData() {

        try (BufferedWriter outputStream = Files.newBufferedWriter(accountsFile.toPath())) {

            for(Account account : accounts.values()) {

                StringBuilder stringBuilder = new StringBuilder();

                // Adds the separator for every piece of data provided by Account
                for(String data : account.writeToStringArray()){
                    stringBuilder.append(data).append(":");
                }

                // Remove the last :
                outputStream.write(stringBuilder.substring(0, stringBuilder.length()-1));

                // Divides each data by newline
                outputStream.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    /**
     * Checks if Password is correct (no case-sensitive)
     * @param user username of the account
     * @param password password of the account
     * @return true or false
     */

    public boolean checkPassword(String user, String password){

        Account account = getAccount(user);

        if(account == null)
            return false;

        return account.getPassword().equals(password);
    }

    /**
     * Checks if Account exists by Username
     * @param username username of the account
     * @return true or false
     */

    public boolean existsUsername(String username) {
        return accounts.containsKey(username);
    }

    /**
     * Gets the account by username
     * @param username username of the account
     * @return Account retrieved from hashmap
     */

    public Account getAccount(String username) {

        // Verify first if it exists or not
        if(existsUsername(username))
            return accounts.get(username);

        return null;
    }

    /**
     * Adds account to hashmap
     * @param account Account
     */

    public void addAccount(Account account){
        accounts.put(account.getUsername(), account);
    }

    /**
     * Gets all Accounts on HashMap
     * @return all Accounts
     */

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }


}
