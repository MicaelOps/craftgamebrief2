package com.micaelops.livebrief2.utils;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;

import java.util.Scanner;


/***
 * This class purpose is to avoid creating
 * the same lines of code in other classes
 */
public class MethodUtils {


    private static MethodUtils instance;
    private Database database;

    private MethodUtils(){
        database = Database.getInstance();
    }

    public static MethodUtils getInstance() {

        if(instance == null)
            instance = new MethodUtils();

        return instance;
    }


    public String getStringFromInput(Scanner scanner, String askMsg , String type, int min) {
        System.out.println(askMsg);
        String input = scanner.nextLine();

        if(input.length() < min) {
            System.out.println("Please input a "+type+" bigger than "+min+" characters");
            return "";
        }
        return input;
    }

    public int getIntFromInput(Scanner scanner, String askMsg , String type, int min) {
        try {
            return Integer.parseInt(getStringFromInput(scanner, askMsg, type, min));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public int getIntFromInput(Scanner scanner, int min) {
        try {
            return Integer.parseInt(getStringFromInput(scanner, "", "", min));
        }catch (NumberFormatException e){
            return 0;
        }
    }


    public ChildAccount[] sortChildren(ChildAccount[] usernames){

        ChildAccount[] sortedList = new ChildAccount[usernames.length];


        for(int sortelement = 0; sortelement < sortedList.length; sortelement++) {

            for(int sortnext = 0; sortnext < sortedList.length; sortnext++) {

                if(usernames[sortelement] == null || usernames[sortnext] == null)
                    continue;

                if(usernames[sortelement].getProgress() < usernames[sortnext].getProgress()){
                    ChildAccount account = usernames[sortnext];
                    sortedList[sortnext] = usernames[sortelement];
                    sortedList[sortelement] = account;
                }
            }
        }
        return sortedList;
    }

    public ChildAccount[] sortChildren(String[] usernames){

        ChildAccount[] sortedList = new ChildAccount[usernames.length];

        for(int i = 0; i < usernames.length; i++) {
            if(database.existsUsername(usernames[i]))
                sortedList[i] = (ChildAccount) Database.getInstance().getAccount(usernames[i]);
        }
        return sortChildren(sortedList);
    }

}
