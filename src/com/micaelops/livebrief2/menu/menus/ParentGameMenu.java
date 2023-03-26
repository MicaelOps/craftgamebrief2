package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.account.ParentAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.Scanner;

public class ParentGameMenu extends Menu {


    // Stages of the menu
    private final int OPTIONS_STAGE = 0, VIEW_PARENT_CHILDREN_STAGE = 1, VIEW_ALL_CHILDRE_STAGE = 2, CHANGE_PIN_STAGE = 3, ADD_CHILD_STAGE = 4;


    private ParentAccount account;

    public ParentGameMenu(ParentAccount account) {
        this.account = account;
    }

    @Override
    public void welcome() {
        System.out.println("Welcome, "+account.getName());
        setStage(OPTIONS_STAGE);
    }


    @Override
    public void process() {

        System.out.println("Choose your options");
        System.out.println("1 - View your children's progression leaderboard");
        System.out.println("2 - View All children leaderboard");
        System.out.println("3 - Change Child's PIN");
        System.out.println("4 - Add child");

        Scanner scanner = new Scanner(System.in);

        MethodUtils methodUtils = MethodUtils.getInstance();

        int option = methodUtils.getIntFromInput(scanner, 1);

        if(option == 0){
            System.out.println("Invalid option");
            return;
        }

        switch (option) {

            case VIEW_PARENT_CHILDREN_STAGE:
                viewParentChildren();
                break;
            case VIEW_ALL_CHILDRE_STAGE:
                break;
            case CHANGE_PIN_STAGE:
                break;
            case ADD_CHILD_STAGE:
                break;
        }
    }
    @Override
    public Menu getNewMenu() {
        return null;
    }

    private void viewParentChildren(){

        String[] children = account.getChildren();

        ChildAccount[] childAccounts = MethodUtils.getInstance().sortChildren(children);

        if(childAccounts[0] == null){
            System.out.println("You don't have any children!");
            return;
        }

        for(int i = 0; i < childAccounts.length; i++) {
            ChildAccount child = childAccounts[i];
            System.out.println(" "+i+" - " + child.getName() +" (Age: "+child.getAge()+")" + " Progress: "+child.getProgress());

        }
    }
}
