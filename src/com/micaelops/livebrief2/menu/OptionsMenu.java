package com.micaelops.livebrief2.menu;

import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * An extension of the Menu interface where it
 * specializes in providing options for the user
 *
 * OptionsMenu extension starts by loading its options on Menu.welcome()
 * Afterwards, on the Menu.process() it prints the options and then the user
 * selects the option
 *
 * Every option is a stage in the menu hence everytime the Menu.process() is finished
 * if the menu is not finished the user will be redirected to that option again.
 *
 *
 */
public abstract class OptionsMenu implements Menu{


    // Hashmap containing all the options
    private final HashMap<Integer, Consumer<Object>> options;

    // Default Options Stage value
    public final int OPTIONS_STAGE;

    // Tracker of menu stages
    private int stage;


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
        setStage(OPTIONS_STAGE);
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
     */
    @Override
    public void setStage(int newstage) {
        this.stage = newstage;
    }


    /**
     * Gets the stage value
     * More documentation on Menu interface
     */

    @Override
    public int getStage() {
        return this.stage;
    }

    /**
     * If menu stage is OPTIONS_STAGE then gets user input and
     * invokes that option (if exists)
     *
     * Otherwise, it will just run that option continuously until it is finished
     *
     * More documentation on Menu interface
     */

    @Override
    public void process() {

        Scanner scanner = new Scanner(System.in);

        if(getStage() == OPTIONS_STAGE) {

            // Prints options
            printOptions();

            int option = MethodUtils.getInstance().getIntFromInput(scanner, 1);

            // Checks if the option is valid
            if(option == 0 || !options.containsKey(option)){
                System.out.println("Invalid option");
                return;
            }

            // Sets new stage
            setStage(option);
        }

        // Adds a few empty lines (platform independent)
        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        // preservers the scanner object if further user input is required
        options.get(getStage()).accept(scanner);
    }



    /**
     * Prints option to user
     */
    public abstract void printOptions();

    /**
     * Puts all the options into the Hashmap
     */
    public abstract void loadOptions();
}
