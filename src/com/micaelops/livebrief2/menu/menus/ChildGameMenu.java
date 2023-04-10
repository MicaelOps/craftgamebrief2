package com.micaelops.livebrief2.menu.menus;


import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;


public class ChildGameMenu extends OptionsMenu {


    private final ChildAccount account;

    private Menu nextMenu;

    public ChildGameMenu(ChildAccount account) {
        this.account = account;
    }

    @Override
    public void printOptions() {
        System.out.println("Choose your options");
        System.out.println("1 - Create new world");
        System.out.println("2 - Load world");
        System.out.println("3 - View leaderboard");
        System.out.println("4 - Exit Game");
    }

    @Override
    public void loadOptions() {
        addOption(1, (obj)-> handleWorld(true));
        addOption(2, (obj)-> handleWorld(false));
        addOption(3, this::printLeaderboard);
        addOption(4, this::exitGame);
    }


    @Override
    public Menu getNewMenu() {
        return nextMenu;
    }

    private void handleWorld(Object isnewworld){

        if((boolean)isnewworld) {

            if(account.findNearestEmptySlot() == 0 && account.getProgress() == 0) {
                System.out.println("You haven't started a world!");
                return;
            }

            account.resetWorldStats();
        }

        nextMenu = new WorldGameMenu(account);
        setStage(FINISHED_STAGE);
    }

    private void exitGame(Object object){
        nextMenu = null;
        setStage(FINISHED_STAGE);
    }

    private void printLeaderboard(Object object){

        setStage(OPTIONS_STAGE);

        ChildAccount[] accounts = MethodUtils.getInstance().sortChildren(Database.getInstance().getAllAccounts().stream().filter(account1 -> account1 instanceof ChildAccount).toArray(ChildAccount[]::new));

        if(accounts[0] == null)
            return;

        System.out.println("--------------- Leaderboard ---------------");

        int place = 1; // starts at the first place

        for(ChildAccount child : accounts) {
            System.out.println(" "+place+" - " + child.getName() +" (Age: "+child.getAge()+")" + " Progress: "+child.getProgress());
            place++;
        }
        System.out.println("--------------- Leaderboard ---------------");
    }
}
