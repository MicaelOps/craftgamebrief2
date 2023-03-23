package com.micaelops.livebrief2;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.menus.HomepageMenu;

import java.util.Scanner;

public class Main {

    private Account currentUser;


    public static void main(String[] args) {

        Database database = new Database();

        if(!database.loadData()) {
            System.out.println("We weren't able to load data.");
            System.exit(-1); // safe to use since it hasnt loaded anything apart from database
        }

        // Beginning Menu
        Menu menu = new HomepageMenu();

        // tracks whether there is no more menu processing
        boolean finished = false;

        while (!finished) {

            // handles input
            menu.process();

            /**
             * Checks whether the menu has no more processing to do
             * If it has then it changes menu
             * If it doesn't then sets the variable finished to true to close the while loop
             */
            if(menu.isFinished()) {
                menu = menu.getNewMenu();
                finished = menu == null;
            }
        }

        System.out.println(System.lineSeparator()); // new line platform independent
        System.out.println("Thank you for using our program!");

    }
}
