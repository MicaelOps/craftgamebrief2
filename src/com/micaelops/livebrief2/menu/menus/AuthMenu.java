package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

public class AuthMenu extends OptionsMenu {


    private final boolean isparent;

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

        username = methodUtils.getStringFromInput((Scanner) objscanner, "Please input your username " ,"username", 5);
        password = methodUtils.getStringFromInput((Scanner) objscanner, "Please input your "+(isparent ? "password" : "PIN"), "password", 5);

        Database database = Database.getInstance();

        if(!database.checkPassword(username, password)) {
            System.out.println("Incorrect Details! Try again.");
            return;
        }

        account = database.getAccount(username);

        System.out.println("Successfully authenticated!");

        setStage(FINISHED_STAGE);
    }


    private void registerAccount(Object scanner) {

        MethodUtils methodUtils = MethodUtils.getInstance();

        String username = methodUtils.getStringFromInput((Scanner) scanner, "Please input a username " ,"username", 5);

        if(Database.getInstance().existsUsername(username)) {
            System.out.println("That username already exists");
            return;
        }

        String name = methodUtils.getStringFromInput((Scanner) scanner, "Please insert your name ", "name", 3);
        int age = methodUtils.getIntFromInput((Scanner) scanner, "Please insert your age", "age", 0);

        if(name.isEmpty() || username.isEmpty() || age == 0) {
            System.out.println("Invalid data detected while creating account! Try again!");
            return;
        }

        if(!isparent) {

            int pin = methodUtils.getIntFromInput((Scanner) scanner, "Please input your PIN", "PIN", 4);

            if(pin == 0) {
                System.out.println("Invalid PIN");
                return;
            }

            account = new ChildAccount(username, ""+pin, name, age, 0L);
        } else {

            String password = methodUtils.getStringFromInput((Scanner) scanner, "Please input your password ", "password", 5);

            if(password.isEmpty()) {
                System.out.println("Invalid password");
                return;
            }

            account = new ParentAccount(username, password, name, age, new String[5]);
        }

        Database.getInstance().addAccount(account);

        System.out.println("Account successfully created!");
        setStage(FINISHED_STAGE);

    }
}

