package com.micaelops.livebrief2;

import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.menus.HomepageMenu;

public class Main {

    public static void main(String[] args) {

        if(!Database.getInstance().loadData()) {
            System.out.println("We weren't able to load data.");
            System.exit(-1); // exit program;
        }

        // Beginning Menu
        Menu menu = new HomepageMenu();

        // executes the welcome menu stage
        menu.welcome();

        // tracks whether there is no more menu processing
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

                menu = menu.getNewMenu();

                if(menu != null)
                    menu.welcome();
                else
                    finished = true;
            }

        } while (!finished);


        System.out.println(System.lineSeparator()); // prints new line, platform independent
        System.out.println("Thank you for using our program!");

    }
}
