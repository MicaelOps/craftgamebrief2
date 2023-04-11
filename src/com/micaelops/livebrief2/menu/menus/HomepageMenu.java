package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;

/***
 * As designed in element 010 the Homepage menu is
 * responsible for identifying
 * whether it is a child or a parent.
 *
 */
public class HomepageMenu extends OptionsMenu {

    // Tracks whether is a ParentAccount or ChildAccount
    private boolean isparent;

    @Override
    public void welcome() {
        System.out.println("Hello, Welcome to our Intelligentcraft Game");
        System.out.println("Make sure to run this on a command line that enables you to leave at any stage with Ctrl + C");
        System.out.println("Please identify yourself by typing one of the options.");
        super.welcome();
    }


    @Override
    public void printOptions() {
        System.out.println("1 - Child");
        System.out.println("2 - Parent");
    }

    @Override
    public void loadOptions() {
        addOption(1, o -> handleOption(false));
        addOption(2, o -> handleOption(true));
    }

    public void handleOption(boolean output){
        isparent = output;
        setStage(FINISHED_STAGE);
    }
    @Override
    public Menu getNewMenu() {
        return new AuthMenu(isparent);
    }

}
