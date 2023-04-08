package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.menu.OptionsMenu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

public class ParentGameMenu extends OptionsMenu {


    private final ParentAccount account;

    public ParentGameMenu(ParentAccount account) {
        this.account = account;
    }

    @Override
    public void printOptions() {
        System.out.println("Choose your options");
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

        String username = MethodUtils.getInstance().getStringFromInput((Scanner) scanner, "Please input your child username", "child", 2);

        if(username.isEmpty() || account.hasChild(username) || !Database.getInstance().existsUsername(username)){
            System.out.println("Invalid username");
            return;
        }

        if(Database.getInstance().getAccount(username) instanceof ParentAccount) {
            System.out.println("That username belongs to a parent.");
            return;
        }
        
        account.addChild(username);

        System.out.println("Your child was added successfully.");
        setStage(OPTIONS_STAGE);

    }
    private void viewChangePin(Object objscanner){

        if(!account.hasChildren()) {
            System.out.println("You don't have any children");
            return;
        }

        System.out.println("Please choose one of your children usernames.");
        System.out.println("Usernames: ");

        for(String children : account.getChildren()){
            System.out.println("    - "+children);
        }

        String child = ((Scanner) objscanner).nextLine();

        if(!account.hasChild(child)) {
            System.out.println("You don't have that child!");
            return;
        }

        System.out.println("Please input the pin for the child: ");

        int pin = MethodUtils.getInstance().getIntFromInput((Scanner) objscanner, "Please input the pin for the child", "PIN", 4);

        if(pin != 0){
            Database.getInstance().getAccount(child).setPassword(""+pin);
            System.out.println("PIN successfully changed!");
            setStage(OPTIONS_STAGE);
        }
    }


    private void printLeaderboard(ChildAccount[] accounts){
        
        setStage(OPTIONS_STAGE);

        if(accounts[0] == null) {
            System.out.println("You don't have any children!");
            return;
        }

        System.out.println("--------------- Leaderboard ---------------");


        for(int i = 0; i < accounts.length; i++) {
            ChildAccount child = accounts[i];
            System.out.println(" "+i+" - " + child.getName() +" (Age: "+child.getAge()+")" + " Progress: "+child.getProgress());

        }
        System.out.println("--------------- Leaderboard ---------------");
    }
}
