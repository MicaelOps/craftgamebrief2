package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.game.ItemType;
import com.micaelops.livebrief2.game.Questions;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Extension of the ChildGameMenu
 * by design everything would be on ChildGameMenu but that would decrease
 * readability of the code.
 *
 * This Menu is responsible for the Child playing the actual game.
 * By design as the child 'explores the world' to collect resources it will
 * be asked questions appropriate to the child's level in order to receive resources
 *
 * In this case the child has to manually choose whether it wants to explore the world
 * or do something else.
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
        System.out.println("1 - Explore World");
        System.out.println("2 - Inventory");
        System.out.println("3 - Craft Items");
    }

    @Override
    public void loadOptions() {
        addOption(1, this::exploreWorld);
        addOption(2, this::showInventory);
        addOption(3, this::craftItems);
    }

    private void exploreWorld(Object scanner){

        // Ensures that the user returns to the options stage
        setStage(OPTIONS_STAGE);

        try {

            System.out.println("Exploring the world....");
            Thread.sleep(3000L); // wait 3 seconds

            System.out.println("You found an item!");
            System.out.println("Before you are able to obtain it, you must answer correctly the question.");

            Questions question = Questions.getQuestionByProgress(account.getProgress());

            if(question == null){
                System.out.println("Something went wrong while getting the question!");
                return;
            }

            if(MethodUtils.getInstance().getStringFromInput((Scanner) scanner, question.getQuestionText(), "answer", 1).equalsIgnoreCase(question.getAnswer())){
                System.out.println("Congratulations! Your answer is correct.");
                System.out.println("You gained " + (question.getProgressRequired()+10) + "XP from getting the correct answer.");

                ItemType itemType = ItemType.getRandomItem();
                System.out.println("You obtained a "+itemType.getName());

                account.setProgress(account.getProgress() + question.getProgressRequired() + 10);
                account.addItem(new Item(itemType, 1));

            } else {

                System.out.println("Incorrect answer...");
                System.out.println("The item mysteriously disappears.....");
                System.out.println("You lost " + (question.getProgressRequired() +10) + "XP from getting the incorrect answer.");
                account.setProgress(account.getProgress() - question.getProgressRequired() - 10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void craftItems(Object scanner){

        showManual();

        // Ensures that the user returns to the options stage
        setStage(OPTIONS_STAGE);

        System.out.println("Which item do you want to craft: ");

        ItemType itemType = ItemType.getItemByName(MethodUtils.getInstance().getStringFromInput((Scanner) scanner, "Which item do you want to craft: ", "Item", 3));

        if(itemType == null){
            System.out.println("Invalid Item name!");
            return;
        }

        for(Item requirements : itemType.getComponents()) {
            if(account.findItemSlot(requirements) == -1) {
                System.out.println("You don't have the necessary items to craft this item!");
                return;
            }
        }

        // Remove components from child's inventory
        Arrays.stream(itemType.getComponents()).forEach(account::removeItem);

        account.addItem(new Item(itemType, 1));

        System.out.println("Item successfully crafted!");

    }


    private void showManual() {

        System.out.println("---------------------- Manual ---------------------- ");

        for(ItemType item : ItemType.getItemsWithComponents()){

            System.out.println(" Name: "+item.getName());
            System.out.println(" Components: ");
            for(Item components : item.getComponents()) {
                System.out.println("    - "+components.getAmount() + " "+components.getItemType().getName() );
            }
        }

        System.out.println("---------------------- Manual ---------------------- ");
    }

    private void showInventory(Object object){

        setStage(OPTIONS_STAGE);

        System.out.println("List of your items: ");

        for(Item item : account.getItems()){
            if(item != null)
                System.out.println("    - Item name: "+item.getItemType().name() + "   Amount: "+item.getAmount());
        }

    }



}
