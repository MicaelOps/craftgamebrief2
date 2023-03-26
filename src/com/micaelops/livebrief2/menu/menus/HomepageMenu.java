package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.menu.Menu;

import java.util.Scanner;


/***
 * As designed in element 010 the Homepage menu is
 * responsible for identifying and authenticate the user
 * whether it is a child or a parent.
 *
 * Stages of the menu
 * 0 (default) = asking options
 * 1 = finished
 *
 */
public class HomepageMenu extends Menu {

    private boolean isparent;

    @Override
    public void welcome() {
        System.out.println("Hello, Welcome to our Intelligentcraft Game");
        System.out.println("Please identify yourself by typing one of the options.");
    }


    @Override
    public void process() {

        System.out.println("Options:");
        System.out.println("    - Child");
        System.out.println("    - Parent");

        Scanner scanner = new Scanner(System.in);
        String usertype = scanner.nextLine();

        if(usertype.equalsIgnoreCase("child")) {
            isparent = false;
            setStage(FINISHED_STAGE);
        } else if(usertype.equalsIgnoreCase("parent")) {
            isparent = true;
            setStage(FINISHED_STAGE);
        } else
            System.out.println("Incorrect option, Try again.");

    }



    @Override
    public Menu getNewMenu() {
        return new AuthMenu(isparent);
    }

}
