package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;

public class WorldGameMenu extends OptionsMenu {


    private final ChildAccount account;

    public WorldGameMenu(ChildAccount account) {
        this.account = account;
    }

    @Override
    public Menu getNewMenu() {
        return null;
    }

    @Override
    public void printOptions() {
        System.out.println("Choose your options:");
        System.out.println("1 - Explore World");
        System.out.println("2 - Inventory");
        System.out.println("3 - Craft Items");
        System.out.println("4 - Exit game");
    }

    @Override
    public void loadOptions() {
        addOption(1, this::exploreWorld);
        addOption(2, this::showInventory);
        addOption(3, this::craftItems);
        addOption(4, this::exitGame);
    }

    private void exploreWorld(Object object){

    }
    private void exitGame(Object object){
        System.out.println("Saving game...");
        Database.getInstance().saveData();
        setStage(FINISHED_STAGE);
    }

    private void craftItems(Object object){

    }

    private void showInventory(Object object){

        if(account.getItems().isEmpty()) {
            System.out.println("You have no items!");
            return;
        }

        System.out.println("List of your items: ");

        for(Item item : account.getItems()){
            System.out.println("    - Item name: "+item.getItemType().name() + "   Amount: "+item.getAmount());

        }
    }
}
