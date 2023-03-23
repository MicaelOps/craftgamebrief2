package com.micaelops.livebrief2.menu;

public interface Menu {

    /**
     * checks if Menu is finished with its processing
     * @return boolean true or false
     */
    boolean isFinished();

    /**
     * Gets the new menu for the Main loop to change
     * @return new Menu
     */
    Menu getNewMenu();

    /**
     * Handles input
     */
    void process();
}
