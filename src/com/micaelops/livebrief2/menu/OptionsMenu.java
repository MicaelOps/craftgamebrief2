package com.micaelops.livebrief2.menu;

import com.micaelops.livebrief2.utils.MethodUtils;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class OptionsMenu implements Menu{


    private final HashMap<Integer, Consumer<Object>> options;


    public final int OPTIONS_STAGE;

    private int stage;


    public OptionsMenu(){
        options = new HashMap<>();
        OPTIONS_STAGE = 0;
    }



    @Override
    public void welcome() {
        loadOptions();
    }


    @Override
    public void setStage(int newstage) {
        this.stage = newstage;
    }


    @Override
    public int getStage() {
        return this.stage;
    }


    @Override
    public void process() {


        Scanner scanner = new Scanner(System.in);

        if(getStage() == OPTIONS_STAGE) {

            printOptions();

            int option = MethodUtils.getInstance().getIntFromInput(scanner, 1);

            if(option == 0 || !options.containsKey(option)){
                System.out.println("Invalid option");
                return;
            }

            setStage(option);
        }

        // Adds a few empty lines (platform independent)
        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());

        options.get(getStage()).accept(scanner);
    }

    public void addOption(int option, Consumer<Object> optionMethod) {
        options.put(option, optionMethod);
    }

    public abstract void printOptions();

    public abstract void loadOptions();
}
