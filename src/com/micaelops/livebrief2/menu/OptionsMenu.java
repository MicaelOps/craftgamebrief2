package com.micaelops.livebrief2.menu;

import com.micaelops.livebrief2.database.Database;
import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * An extension of the Menu interface where it
 * specializes in providing options for the user
 *
 * OptionsMenu extension starts by loading its options on Menu.welcome()
 * and on the Menu.process() it prints the options whilst providing
 * an automatic exit option for the user
 *
 * Every OptionsMenu has an exit option for the user to quit the game
 *
 * Every option is a stage in the menu, hence everytime the Menu.process() is finished
 * and the menu is not finished the user will be redirected to the last stage set
 *
 */
public abstract class OptionsMenu implements Menu{


    // Hashmap containing all the options and its methods of execution
    private final HashMap<Integer, Consumer<Object>> options;

    // Default Options Stage value
    public final int OPTIONS_STAGE;

    // Tracker of menu stages
    private int stage;

    // Tracker of whether the user has chosen to exit the game
    private boolean exit = false;

    /**
     * Initializing variables
     */
    public OptionsMenu(){
        options = new HashMap<>();
        OPTIONS_STAGE = 0;
    }

    /**
     * Loads options and sets the stage to OPTIONS_STAGE
     * More documentation on Menu interface
     */
    @Override
    public void welcome() {
        loadOptions();

        // Ensures the user starts at the Options stage
        setStage(OPTIONS_STAGE);

        // Adds the final exit option for the user to exit at any time
        options.put(options.size() + 1, this::handleExit);
    }

    /**
     * Adds option to the hashmap
     * @param option option unique identifier
     * @param optionMethod method
     */

    public void addOption(int option, Consumer<Object> optionMethod) {
        options.put(option, optionMethod);
    }

    /**
     * Sets the stage value
     * More documentation on Menu interface
     * @param newstage new stage Integer value
     */

    @Override
    public void setStage(int newstage) {
        this.stage = newstage;
    }

    /**
     * Gets the stage value
     * More documentation on Menu interface
     * @return Integer value of the stage
     */

    @Override
    public int getStage() {
        return this.stage;
    }

    /**
     * Gets the user to input a valid option
     * And changes the menu stage accordingly
     *
     * If the user remains on the same stage after completing the stage
     * asks if the user wants to go back to the Options stage.
     *
     * More documentation on Menu interface
     */

    @Override
    public void process() {

        Scanner scanner = new Scanner(System.in);

        if(getStage() == OPTIONS_STAGE) {

            System.out.println("Choose your options");

            // Print options
            printOptions();

            System.out.println(options.size() + " - Exit");

            int option = MethodUtils.getInstance().getIntFromInput(scanner, 1);

            // Checks if the option is valid
            if(option == 0 || !options.containsKey(option)) {
                System.out.println("Invalid option");
                return;
            }

            // Sets new stage
            setStage(option);
        }

        // Adds a few empty lines (platform independent)
        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        // Executes the option
        // preserves the scanner object if further user input is required
        options.get(getStage()).accept(scanner);

        /*
         * If something went wrong during the execution of the option
         * and the menu wants the user to repeat the stage, this section will
         * ensure the user can always return to the option stage and not stay stuck
         * trying to fix the problem.
         */

        if(getStage() != OPTIONS_STAGE && getStage() != FINISHED_STAGE){

            String answer = "valid";

            // persists for a correct answer
            while(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")){
                answer = MethodUtils.getInstance().getStringFromInput(scanner, "Do you wish to return to the menu options? [y/n]", "answer", 1);
            }

            // Returns to Option stage
            if(answer.equalsIgnoreCase("y"))
                setStage(OPTIONS_STAGE);

        }
    }

    /**
     * Checks if the user has inputted the exit Option
     * @return true or false
     */

    public boolean isExit(){
        return exit;
    }

    /**
     * Handles the Exit Option
     * @param object Scanner
     */

    private void handleExit(Object object) {

        // Ensures data is saved
        Database.getInstance().saveData();

        setStage(FINISHED_STAGE);
        exit = true;
    }

    /**
     * Gets new Menu while ensuring that the user
     * still wants to play the game
     * @return new Menu
     */

    @Override
    public Menu nextMenu() {
        return isExit() ? null : getNewMenu();
    }

    /**
     * Prints option to user
     */

    public abstract void printOptions();

    /**
     * Puts all the options into the Hashmap
     */

    public abstract void loadOptions();

    /**
     * Gets the next new Menu
     * @return new Menu
     */

    public abstract Menu getNewMenu();

}
