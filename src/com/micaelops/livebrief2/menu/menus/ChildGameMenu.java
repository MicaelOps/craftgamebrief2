package com.micaelops.livebrief2.menu.menus;


import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;


/**
 * ChildGame menu
 */
public class ChildGameMenu extends OptionsMenu {


    // Tracks the current account being used
    private final ChildAccount account;

    public ChildGameMenu(ChildAccount account) {
        this.account = account;
    }

    @Override
    public void printOptions() {
        System.out.println("1 - Create new world");
        System.out.println("2 - Load world");
        System.out.println("3 - View leaderboard");
    }

    @Override
    public void loadOptions() {
        addOption(1, (obj)-> handleWorld(true));
        addOption(2, (obj)-> handleWorld(false));
        addOption(3, this::printLeaderboard);
    }

    @Override
    public Menu getNewMenu() {
        return new WorldGameMenu(account);
    }

    private void handleWorld(boolean isNewWorld){

        //
        if(isNewWorld) {

            System.out.println("Welcome to the new world!");
            System.out.println("Daily Bonus EXP for creating new world: 1 XP");

            // Reset stats
            account.resetWorldStats();

            // Ensures that this is no longer the first time
            account.setProgress(1);

            // Checking if the player has created a world first
        } else if(account.isFirstTime()) {

            // Makes the user return to Options stage
            setStage(OPTIONS_STAGE);

            System.out.println("You haven't started a world!");
            return;
        }

        // Finishes the menu
        setStage(FINISHED_STAGE);
    }


    private void printLeaderboard(Object object){

        // Sorts children accounts
        ChildAccount[] accounts = MethodUtils.getInstance().sortChildren(Database.getInstance().getAllAccounts().stream().filter(account1 -> account1 instanceof ChildAccount).toArray(ChildAccount[]::new));

        System.out.println("--------------- Leaderboard ---------------");

        int place = 1; // starts at the first place

        for(ChildAccount child : accounts) {
            System.out.println(" "+place+" - " + child.getName() +" (Age: "+child.getAge()+")" + " Progress: "+child.getProgress()+"XP");
            place++;
        }
        System.out.println("--------------- Leaderboard ---------------");

        // Makes the user return to Options stage
        setStage(OPTIONS_STAGE);

    }
}
