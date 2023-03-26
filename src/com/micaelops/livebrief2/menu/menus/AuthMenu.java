package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

public class AuthMenu extends Menu {


    private final boolean isparent;

    // Stages of the menu
    private final int OPTIONS_STAGE = 0, LOGIN_STAGE = 1, REGISTER_STAGE = 2;

    private Account account;


    public AuthMenu(boolean isparent) {
        this.isparent = isparent;

    }

    @Override
    public void welcome() {
        System.out.println("Choose your options: ");
        setStage(OPTIONS_STAGE);
    }

    @Override
    public void process() {

        if(getStage() == OPTIONS_STAGE)
            optionStage();

        else if(getStage() == LOGIN_STAGE)
            loginAccount();

        else if(getStage() == REGISTER_STAGE)
            registerAccount();


    }

    @Override
    public Menu getNewMenu() {
        return isparent ? new ParentGameMenu((ParentAccount) account) : new ChildGameMenu((ChildAccount)account);
    }

    private void optionStage(){

        System.out.println("1 - Register new account ");
        System.out.println("2 - Log in");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            int result = Integer.parseInt(input);

            if(result < 1 || result > 2) {
                System.out.println("Please input a correct number");
                return;
            }

            setStage(result);

        } catch (NumberFormatException e){
            System.out.println("Please input a correct number");
        }
    }

    private void loginAccount(){

        System.out.println("Please input username: ");

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.println("Please input your "+(isparent ? "Password" : "PIN")+": ");

        String pin = scanner.nextLine();

        Database database = Database.getInstance();

        if(database.checkPassword(username, pin)) {

            account = database.getAccount(username);
            System.out.println("Successfully authenticated!");

            setStage(FINISHED_STAGE);

        } else System.out.println("Incorrect Details! Try again!");
    }


    private void registerAccount() {

        Scanner scanner = new Scanner(System.in);
        MethodUtils methodUtils = MethodUtils.getInstance();

        String username = methodUtils.getStringFromInput(scanner, "Please input a username " ,"username", 5);

        Database database = Database.getInstance();

        if(database.existsUsername(username)) {
            System.out.println("That username already exists");
            return;
        }

        String name = methodUtils.getStringFromInput(scanner, "Please insert your name ", "name", 3);
        int age = methodUtils.getIntFromInput(scanner, "Please insert your age", "age", 0);

        if(name.isEmpty() || username.isEmpty() || age == 0) {
            System.out.println("Invalid data detected while creating account! Try again!");
            return;
        }

        if(!isparent) {

            int pin = methodUtils.getIntFromInput(scanner, "Please input your PIN", "PIN", 4);

            if(pin == 0)
                return;

            account = new ChildAccount(username, ""+pin, name, age, 0L);
        } else {

            String password = methodUtils.getStringFromInput(scanner, "Please input your password ", "password", 5);

            if(password.isEmpty())
                return;

            account = new ParentAccount(username, password, name, age, new String[5]);
        }

        System.out.println("Account successfully created!");
        setStage(FINISHED_STAGE);

    }



}

