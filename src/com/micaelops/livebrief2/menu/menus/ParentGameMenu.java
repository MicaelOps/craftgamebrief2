package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

public class ParentGameMenu extends OptionsMenu {


    // Tracker current account being used
    private final ParentAccount account;

    public ParentGameMenu(ParentAccount account) {
        this.account = account;
    }

    @Override
    public void printOptions() {
        System.out.println("1 - View your children's progression leaderboard");
        System.out.println("2 - View All children leaderboard");
        System.out.println("3 - Change Child's PIN");
        System.out.println("4 - Add child");
    }

    @Override
    public void loadOptions() {
        addOption(1, o -> printLeaderboard(MethodUtils.getInstance().sortChildren(account.getChildren())));
        addOption(2, o -> printLeaderboard(MethodUtils.getInstance().sortChildren(Database.getInstance().getAllAccounts().stream().filter(account1 -> account1 instanceof ChildAccount).toArray(ChildAccount[]::new))));
        addOption(3, this::viewChangePin);
        addOption(4, this::addChildren);
    }

    @Override
    public Menu getNewMenu() {
        return null;
    }

    private void addChildren(Object scanner) {

        Database database = Database.getInstance();

        // Ask the parent the child's username
        String username = MethodUtils.getInstance().getStringFromInput((Scanner) scanner, "Please input your child username", "username", 2);

        // Check if child exists in Database and if it is not already added
        if(username.isEmpty() || account.hasChild(username) || !database.existsUsername(username)){
            System.out.println("Invalid username");
            return;
        }

        // Check if username does not belong to a parent
        if(database.getAccount(username) instanceof ParentAccount) {
            System.out.println("That username belongs to a parent.");
            return;
        }

        // Check if that child already belongs to another parent
        if(database.getAllAccounts().stream().anyMatch(allAccounts -> allAccounts instanceof ParentAccount && ((ParentAccount) allAccounts).hasChild(username))){
            System.out.println("That child belongs to another parent!");
            return;
        }

        // Add child
        account.addChild(username);

        System.out.println("Your child was added successfully.");

        // Return to options stage
        setStage(OPTIONS_STAGE);

    }
    private void viewChangePin(Object objscanner){

        // Check if Parent has any children
        if(!account.hasChildren()) {
            System.out.println("You don't have any children");
            setStage(OPTIONS_STAGE);
            return;
        }

        System.out.println("Usernames: ");

        // Print all valid children in parent's list
        for(String children : account.getChildren()){

            if(!children.equalsIgnoreCase("null"))
                System.out.println("    - "+children);

        }

        System.out.println("Please choose one of your children usernames.");

        String child = ((Scanner) objscanner).nextLine();

        // Check if child belongs to the list
        if(!account.hasChild(child)) {
            System.out.println("You don't have that child!");
            return;
        }

        int pin = MethodUtils.getInstance().getIntFromInput((Scanner) objscanner, "Please input the new pin for the child:", "PIN", 4);

        // Validate Input
        if(pin != 0) {

            // Change password on Hashmap
            Database.getInstance().getAccount(child).setPassword(""+pin);

            System.out.println("PIN successfully changed!");

            // Return to options stage
            setStage(OPTIONS_STAGE);
        }
    }


    private void printLeaderboard(ChildAccount[] accounts){

        // Check if there is any children in the list
        if(accounts[0] == null) {
            System.out.println("No child was found to display.");
            return;
        }

        System.out.println("--------------- Leaderboard ---------------");

        for(int i = 0; i < accounts.length; i++) {

            ChildAccount child = accounts[i];

            // Print only valid children
            if(child != null)
                System.out.println(" "+(i+1)+" - " + child.getName() +" (Age: "+child.getAge()+")" + " Progress: "+child.getProgress()+"XP");

        }
        System.out.println("--------------- Leaderboard ---------------");

        // Return to options
        setStage(OPTIONS_STAGE);
    }
}
