package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.game.ItemType;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;


/**
 * Extension of the ChildGameMenu
 *
 *
 */
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
    private void craftItems(Object scanner){
        showManual();

        System.out.println("Which item do you want to craft: ");

        ItemType itemType = ItemType.getItemByName(MethodUtils.getInstance().getStringFromInput((Scanner) scanner, "Which item do you want to craft: ", "Item", 3));

        if(itemType == null){
            System.out.println("Invalid Item name!");
            return;
        }

        setStage(OPTIONS_STAGE);
    }

    private void showManual() {
        System.out.println("---------------------- Manual ---------------------- ");
        for(ItemType item : ItemType.getItemsWithComponents()){

            System.out.println(" Name: "+item.getName());

            for(Item components : item.getComponents()) {
                System.out.println("    - "+components.getAmount() + " "+components.getItemType().getName() );
            }
        }
        System.out.println("---------------------- Manual ---------------------- ");
    }

    private void showInventory(Object object){

        setStage(OPTIONS_STAGE);

        if(account.getItems().isEmpty()) {
            System.out.println("You have no items!");
            return;
        }

        System.out.println("List of your items: ");

        for(Item item : account.getItems()){
            System.out.println("    - Item name: "+item.getItemType().name() + "   Amount: "+item.getAmount());
        }

    }

    /**
     * Exi
     * @param object
     */
    private void exitGame(Object object){
        System.out.println("Saving game...");
        Database.getInstance().saveData();
        setStage(FINISHED_STAGE);
    }

}
