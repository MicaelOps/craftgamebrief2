package com.micaelops.livebrief2.menu;

public abstract class Menu {


    // Default Finished stage
    protected final int FINISHED_STAGE = 100;

    // stage of the menu
    private int stage = 0;

    /**
     * Welcome greeting for the menu.
     * Only runs once.
     */
    public abstract void welcome();

    /**
     * Handles input
     * This method always runs until stage of the menu is finished
     */
    public abstract void process();

    /**
     * Gets the new menu for the Main loop to change
     * @return new Menu
     */
    public abstract Menu getNewMenu();


    /**
     * checks if Menu is finished with its processing
     * @return boolean true or false
     */
    public boolean isFinished() {
        return getStage() == FINISHED_STAGE;
    };


    /**
     * Tracks the stage of where the menu is at.
     * @return
     */
    public int getStage(){
        return stage;
    }

    /**
     * Sets a new stage of the menu
     * @param stage
     */
    public void setStage(int stage) {
        this.stage = stage;
    }
}
