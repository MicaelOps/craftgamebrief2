package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.menu.Menu;

import java.util.Scanner;


/***
 * As designed in element 010 the Homepage menu is
 * responsible for identifying and authenticate the user
 * whether it is a child or a parent.
 *
 * Stages of the menu
 * 0 = print hello world messages
 * 1 =
 *
 */
public class HomepageMenu extends Menu {

    private Account account;

    @Override
    public void welcome() {
        System.out.println("Hello, Welcome to our Intelligentcraft Game");
        System.out.println("Please identify yourself by typing one of the options.");
        setStage(HomepageStage.USERDECISION.getStage());
    }

    @Override
    public boolean isFinished() {
        return getStage() == HomepageStage.FINISHED.getStage();
    }

    @Override
    public Menu getNewMenu() {
        return account instanceof ChildAccount ? new ChildGameMenu(account) : new ParentGameMenu(account);
    }

    @Override
    public void process() {

        if(getStage() == HomepageStage.USERDECISION.getStage()) {

            System.out.println("Options:");
            System.out.println("    - Child");
            System.out.println("    - Parent");

            Scanner scanner = new Scanner(System.in);
            String usertype = scanner.nextLine();

            if(usertype.equalsIgnoreCase("child"))
                setStage(HomepageStage.CHILDAUTH.getStage());
            else if(usertype.equalsIgnoreCase("parent"))
                setStage(HomepageStage.AUTH.getStage());
            else
                System.out.println("Incorrect option, Try again.");

            // tricky way to keep the user on the authentication stage while keeping the usertype remembered
        } else if (getStage() > HomepageStage.FINISHED.ordinal())
            handleAuth();

    }

    private void handleAuth(){

        String pinOrpassword = getStage() == HomepageStage.CHILDAUTH.getStage() ? "PIN" : "password";

        System.out.println("Please input username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please input your "+pinOrpassword+": ");
        String pin = scanner.nextLine();

        Database database = Database.getInstance();

        if(database.checkPassword(username, pin)) {

            account = database.getAccount(username);
            setStage(HomepageStage.FINISHED.getStage());
            System.out.println("Successfully authenticated!");

        } else System.out.println("Incorrect Details! Try again!");
    }

    private enum HomepageStage {
        USERDECISION, FINISHED, CHILDAUTH, AUTH;

        public int getStage(){
            return ordinal();
        }
    }

}
