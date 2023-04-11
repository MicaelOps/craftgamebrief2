package com.micaelops.livebrief2.menu;

/**
 * This class purpose is to introduce a menu system
 * where it handles user input.
 *
 * Every menu is divided into stages where
 * 'FINISHED_STAGE' represents when the menu is finished
 *
 * Every menu starts with a welcome greeting method executed only once.
 * After that each created menu does its own processing until the stage is set to 'FINISHED_STAGE'
 *
 * Once the menu is finished the Main loop will change to the new menu (if exists) and run it.
 *
 */
public interface Menu {


    // Default Finished stage value
    int FINISHED_STAGE = 100;


    /**
     * Welcome greeting for the menu.
     * Only runs once.
     */
    void welcome();

    /**
     * Handles menu
     * This method always runs until stage of the menu is finished
     */
    void process();


    /**
     * Sets a new stage of the menu
     * @param newstage value
     */
    void setStage(int newstage);


    /**
     * Tracks the stage of where the menu is at.
     * @return stage value
     */
    int getStage();

    /**
     * Gets the new menu for the Main loop to change
     * @return new Menu
     */
    Menu nextMenu();

    /**
     * checks if the menu is finished
     * @return boolean true or false
     */
    default boolean isFinished() {
        return getStage() == FINISHED_STAGE;
    }

}
