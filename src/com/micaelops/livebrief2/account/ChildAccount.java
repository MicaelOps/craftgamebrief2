package com.micaelops.livebrief2.account;

import com.micaelops.livebrief2.game.Item;

import java.util.ArrayList;

public class ChildAccount extends Account{

    private long progress;
    private ArrayList<Item> items;

    public ChildAccount(String username, String password, String name, int age, long progress) {
        super(username, password, name, age);
        this.progress = progress;
    }

    public ChildAccount(){}

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public void resetWorldStats(){
        setProgress(0);
    }

    @Override
    public Account readFromStringArray(String[] data) {
        return null;
    }

    @Override
    public String writeToString() {
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
