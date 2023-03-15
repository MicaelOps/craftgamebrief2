package com.micaelops.livebrief2;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.database.Database;

import java.util.Scanner;

public class Main {

    private final int CHILD_LOGIN_OPTION = 0, PARENT_LOGIN_OPTION = 1, GAMEMENU_OPTION = 3 , QUIT_OPTION = 4, HOME_OPTION = 5;

    private Account currentUser;
    private int currentoption = HOME_OPTION;


    public static void main(String[] args) {

        Database database = new Database();

        if(!database.loadData()) {
            System.out.println("We weren't able to load data.");
            System.exit(-1);
        }

        printWelcomeMessages();

        while (true) {

        }

        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.println("Enter username: ");

        System.out.println("Enter password: ");


        username = scanner.nextLine();

    }

    private static void printWelcomeMessages(){

    }
}
