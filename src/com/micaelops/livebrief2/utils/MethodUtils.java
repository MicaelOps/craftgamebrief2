package com.micaelops.livebrief2.utils;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;

import java.util.Scanner;

/***
 * The purpose of this class is to avoid creating
 * the same lines of code for different uses
 */
public class MethodUtils {

    // Singleton design
    private static MethodUtils instance;

    // Database instance
    private final Database database;

    private MethodUtils(){
        database = Database.getInstance();
    }

    // Singleton design
    public static MethodUtils getInstance() {

        if(instance == null)
            instance = new MethodUtils();

        return instance;
    }

    /**
     * Prints a message to the user before
     * Getting input from command line and validates with a
     * certain minimum characters required and returns as String
     *
     * @param scanner Scanner object
     * @param askMsg Introduction message before taking the input from the user
     * @param type specifies what kind of data we are dealing with
     * @param min characters required for the input to be considered valid
     * @return If minimum characters is satisfied return String of the input from commandline otherwise empty String
     */

    public String getStringFromInput(Scanner scanner, String askMsg , String type, int min) {

        System.out.println(askMsg);

        String input = scanner.nextLine();

        if(input.length() < min) {
            System.out.println("Please input a "+type+" bigger than "+min+" characters");
            return "";
        }
        return input;
    }

    /**
     * Prints a message to the user before
     * Getting input from command line and validates with a
     * certain minimum characters required and returns as integer
     *
     * @param scanner Scanner object
     * @param askMsg Introduction message before taking the input from the user
     * @param type specifies what kind of data we are dealing with
     * @param min characters required for the input to be considered valid
     * @return If minimum characters is satisfied return int of the input from commandline otherwise 0
     */

    public int getIntFromInput(Scanner scanner, String askMsg , String type, int min) {
        try {
            return Integer.parseInt(getStringFromInput(scanner, askMsg, type, min));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    /**
     * Gets input from command line and validates input with
     * certain minimum characters required and returns as integer
     *
     * Any invalid input returns as 0.
     *
     * @param scanner Scanner object
     * @param min characters required for the input to be considered valid
     * @return If minimum characters is satisfied return int of the input from commandline otherwise 0
     */

    public int getIntFromInput(Scanner scanner, int min) {
        try {
            return Integer.parseInt(getStringFromInput(scanner, "", "", min));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    /**
     * Sorts an array of ChildAccounts
     * Bubble sort style.
     *
     * @param usernames array that contains the childaccounts
     * @return sorted array
     */

    public ChildAccount[] sortChildren(ChildAccount[] usernames){

        for(int sortelement = 0; sortelement < usernames.length; sortelement++) {

            for(int sortnext = 0; sortnext < usernames.length; sortnext++) {

                if(usernames[sortelement] == null || usernames[sortnext] == null)
                    continue;

                if(usernames[sortelement].getProgress() <= usernames[sortnext].getProgress()){
                    ChildAccount account = usernames[sortnext];
                    usernames[sortnext] = usernames[sortelement];
                    usernames[sortelement] = account;
                }
            }
        }
        return usernames;
    }

    /**
     * Sorts usernames by their ChildAccount progress in a
     * Bubble sort style.
     *
     * @param usernames String array that contains the usernames
     * @return sorted array
     */

    public ChildAccount[] sortChildren(String[] usernames){

        ChildAccount[] sortedList = new ChildAccount[usernames.length];

        for(int i = 0; i < usernames.length; i++) {
            if(database.existsUsername(usernames[i]))
                sortedList[i] = (ChildAccount)database.getAccount(usernames[i]);
        }
        return sortChildren(sortedList);
    }
}
