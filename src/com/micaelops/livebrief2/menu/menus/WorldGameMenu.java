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
 * by design everything would be on ChildGameMenu but that would decrease the
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

    // Current account being used to play the game
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

            // Wait 2 seconds
            Thread.sleep(2000L);

            System.out.println("You found an item!");
            System.out.println("Before you are able to obtain it, you must answer correctly the question.");

            // Get question appropriate with child's progress
            Questions question = Questions.getQuestionByProgress(account.getProgress());

            // Validate
            if(question == null){
                System.out.println("Something went wrong while getting the question!");
                return;
            }

            // Ask and check the answer
            if(MethodUtils.getInstance().getStringFromInput((Scanner) scanner, question.getQuestionText(), "answer", 1).equalsIgnoreCase(question.getAnswer())){

                System.out.println("Congratulations! Your answer is correct.");
                System.out.println("You gained " + (question.getProgressRequired()+10) + "XP from getting the correct answer.");

                // Generate Random item appropriate to child progress
                ItemType itemType = ItemType.getRandomItem(account.getProgress());
                System.out.println("You obtained a "+itemType.getName());

                // Change Account values
                account.setProgress(account.getProgress() + question.getProgressRequired() + 10);
                account.addItem(new Item(itemType, 1));

            } else {

                System.out.println("Incorrect answer...");
                System.out.println("The item mysteriously disappears.....");
                System.out.println("You lost " + (question.getProgressRequired() +10) + "XP from getting the incorrect answer.");

                // Lose XP for incorrect answer
                account.setProgress(account.getProgress() - question.getProgressRequired() - 10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void craftItems(Object scanner){

        // Check if inventory is full
        if(account.findNearestEmptySlot() == -1){
            System.out.println("Inventory is full!");
            setStage(OPTIONS_STAGE);
            return;
        }

        // Show list of items that are possible to craft
        showManual();

        // Get desired Item to craft from user input
        ItemType itemType = ItemType.getItemByName(MethodUtils.getInstance().getStringFromInput((Scanner) scanner, "Which item do you want to craft: ", "Item", 3));

        // Validate input
        if(itemType == null){
            System.out.println("Invalid Item name!");
            return;
        }

        // Ensure the child has enough progress to unlock this item
        if(itemType.getProgressRequired() > account.getProgress()) {
            System.out.println("Your progress is to low to craft this item!");
            return;
        }

        // Check if user has the components
        for(Item requirements : itemType.getComponents()) {
            if(account.findItemSlot(requirements) == -1) {
                System.out.println("You don't have the necessary items to craft this item!");
                return;
            }
        }

        // Remove components from child's inventory
        Arrays.stream(itemType.getComponents()).forEach(account::removeItem);

        // Add Item to inventory
        account.addItem(new Item(itemType, 1));

        System.out.println("Item successfully crafted!");

        // Return to Options stage
        setStage(OPTIONS_STAGE);
    }


    private void showManual() {

        System.out.println("---------------------- Manual ---------------------- ");

        // Print all Items with components
        for(ItemType item : ItemType.getItemsWithComponents()){

            // Print only items that child has unlocked
            if(item.getProgressRequired() > account.getProgress())
                continue;

            System.out.println(" Name: "+item.getName());
            System.out.println(" Components: ");

            for(Item components : item.getComponents()) {
                System.out.println("    - "+components.getAmount() + " "+components.getItemType().getName() );
            }
        }

        System.out.println("---------------------- Manual ---------------------- ");
    }

    private void showInventory(Object object){

        System.out.println("---------------------- Inventory ---------------------- ");

        // Print every non-null item in the inventory
        for(Item item : account.getItems()){
            if(item != null)
                System.out.println("    - Item name: "+item.getItemType().name() + "   Amount: "+item.getAmount());
        }

        System.out.println("---------------------- Inventory ---------------------- ");

        // Return to Options stage
        setStage(OPTIONS_STAGE);

    }
}
