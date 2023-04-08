package com.micaelops.livebrief2.menu;

public interface Menu {


    // Default Finished stage
    int FINISHED_STAGE = 100;


    /**
     * Welcome greeting for the menu.
     * Only runs once.
     */
    void welcome();

    /**
     * Handles input
     * This method always runs until stage of the menu is finished
     */
    void process();


    /**
     * Sets a new stage of the menu
     * @param stage
     */
    void setStage(int newstage);


    /**
     * Gets the new menu for the Main loop to change
     * @return new Menu
     */
    Menu getNewMenu();

    /**
     * Tracks the stage of where the menu is at.
     * @return
     */
    int getStage();

    /**
     * checks if Menu is finished with its processing
     * @return boolean true or false
     */
    default boolean isFinished() {
        return getStage() == FINISHED_STAGE;
    }

}
