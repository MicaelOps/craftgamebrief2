package com.micaelops.livebrief2.utils;

import com.micaelops.livebrief2.account.ChildAccount;
import com.micaelops.livebrief2.database.Database;

import java.util.Scanner;


/***
 * This class purpose is to avoid creating
 * the same lines of code for different purposes
 */
public class MethodUtils {


    // Singleton design
    private static MethodUtils instance;

    // Database instance
    private Database database;

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
     * Gets input from command line and validates input with
     * certain minimum characters required and returns as text
     *
     * Any invalid input returns as empty string.
     *
     * @param scanner Scanner object
     * @param askMsg Introduction message before taking the input from the user
     * @param type specifies what kind of data we are dealing with
     * @param min characters required for the input to be considered valid
     * @return input for text
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
     * Gets input from command line and validates input with
     * certain minimum characters required and returns as integer
     *
     * Any invalid input returns as 0.
     *
     * @param scanner Scanner object
     * @param askMsg Introduction message before taking the input from the user
     * @param type specifies what kind of data we are dealing with
     * @param min characters required for the input to be considered valid
     * @return input for text
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
     * @return input for text
     */

    public int getIntFromInput(Scanner scanner, int min) {
        try {
            return Integer.parseInt(getStringFromInput(scanner, "", "", min));
        }catch (NumberFormatException e){
            return 0;
        }
    }

    /**
     * Sorts ChildAcoount by their progress in a
     * bubble sort style.
     *
     * @param usernames array that contains the childaccounts
     * @return sorted array
     */
    public ChildAccount[] sortChildren(ChildAccount[] usernames){

        ChildAccount[] sortedList = new ChildAccount[usernames.length];

        for(int sortelement = 0; sortelement < sortedList.length; sortelement++) {

            for(int sortnext = 0; sortnext < sortedList.length; sortnext++) {


                if(usernames[sortelement] == null || usernames[sortnext] == null)
                    continue;

                if(usernames[sortelement].getProgress() <= usernames[sortnext].getProgress()){
                    ChildAccount account = usernames[sortnext];
                    sortedList[sortnext] = usernames[sortelement];
                    sortedList[sortelement] = account;
                }
            }
        }
        return sortedList;
    }

    /**
     * Sorts usernames by their ChildAccount progress in a
     * bubble sort style.
     *
     * @param usernames array that contains the usernames
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
