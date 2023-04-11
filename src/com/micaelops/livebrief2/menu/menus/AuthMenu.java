package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

/**
 * Authentication Menu
 * Responsible for Authenticating the user
 *
 * Information about the overridden methods in OptiosMenu class
 */
public class AuthMenu extends OptionsMenu {


    // Tracker whether we are dealing with a child account or parent account
    private final boolean isparent;

    // Final account output after registering/logging
    private Account account;


    public AuthMenu(boolean isparent) {
        this.isparent = isparent;
    }

    @Override
    public void printOptions() {
        System.out.println("1 - Register new account ");
        System.out.println("2 - Log in");
    }

    @Override
    public void loadOptions() {
        addOption(1, this::registerAccount);
        addOption(2, this::loginAccount);
    }

    @Override
    public Menu getNewMenu() {
        return isparent ? new ParentGameMenu((ParentAccount) account) : new ChildGameMenu((ChildAccount)account);
    }


    private void loginAccount(Object objscanner){

        MethodUtils methodUtils = MethodUtils.getInstance();

        String username, password;

        // Asks user information

        username = methodUtils.getStringFromInput((Scanner) objscanner, "Please input your username " ,"username", 5);
        password = methodUtils.getStringFromInput((Scanner) objscanner, "Please input your "+(isparent ? "password" : "PIN"), "password", 3);

        Database database = Database.getInstance();

        // Validates Information
        if(!database.checkPassword(username, password)) {
            System.out.println("Incorrect Details! Try again.");
            return;
        }

        // Get the account from database
        account = database.getAccount(username);

        // Makes sure it is not a child logging in to a Parent account or vice versa
        if((isparent && account instanceof ChildAccount) || (!isparent && account instanceof ParentAccount)){
            System.out.println("You are not allowed to access other people account!");
            return;
        }

        System.out.println("Successfully authenticated!");

        // Finishes the menu
        setStage(FINISHED_STAGE);
    }


    private void registerAccount(Object scanner) {

        MethodUtils methodUtils = MethodUtils.getInstance();

        String username = methodUtils.getStringFromInput((Scanner) scanner, "Please input a username " ,"username", 5);

        // Validate input
        if(username.isEmpty())
            return;

        // Avoid creating an account with same username
        if(Database.getInstance().existsUsername(username)) {
            System.out.println("That username already exists");
            return;
        }

        // Ask for information
        String name = methodUtils.getStringFromInput((Scanner) scanner, "Please insert your name ", "name", 3);
        int age = methodUtils.getIntFromInput((Scanner) scanner, "Please insert your age", "age", 0);

        // Validate Input
        if(name.isEmpty() || age == 0) {
            System.out.println("Invalid data detected. Cancelling process...");
            return;
        }

        // Ask for pin or password according to type of account

        if(!isparent) {

            int pin = methodUtils.getIntFromInput((Scanner) scanner, "Please input your PIN", "PIN", 3);

            // Validating input
            if(pin == 0) {
                System.out.println("Invalid data detected. Cancelling process...");
                return;
            }

            account = new ChildAccount(username, ""+pin, name, age, 0L, new Item[27]);

        } else {

            String password = methodUtils.getStringFromInput((Scanner) scanner, "Please input your password ", "password", 5);

            // Validating input
            if(password.isEmpty()) {
                System.out.println("Invalid data detected. Cancelling process...");
                return;
            }

            account = new ParentAccount(username, password, name, age, new String[10]);
        }

        // Add to database
        Database.getInstance().addAccount(account);

        System.out.println("Account successfully created!");

        // Finishes the menu
        setStage(FINISHED_STAGE);

    }
}

