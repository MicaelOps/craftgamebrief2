package com.micaelops.livebrief2;

import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.menus.HomepageMenu;


public class Main {

    public static void main(String[] args) {

        // Loads Data
        if(!Database.getInstance().loadAccounts()) {
            System.out.println("We weren't able to load accounts.");
            System.exit(-1);
        }

        // Beginning Menu
        Menu menu = new HomepageMenu();

        // executes the homepage menu welcome
        menu.welcome();

        // tracks whether there is no more menus to process
        boolean finished = false;

        do {

            // handles input
            menu.process();

            /*
             * Checks whether the menu has finished
             * If the menu is finished then checks if there is a new menu to
             * change otherwise closes the loop by setting the 'finished' variable to true.
             *
             * If the menu has not finished then continues the loop
             */
            if(menu.isFinished()) {

                menu = menu.nextMenu();

                // Adds a few empty lines (platform independent)
                System.out.println(System.lineSeparator());
                System.out.println(System.lineSeparator());

                if(menu != null)
                    menu.welcome();
                else
                    finished = true;
            }

        } while (!finished);

        /* When this section is reached the program saves data and closes */

        Database.getInstance().saveData();
        System.out.println("Thank you for using our program!");
    }
}
