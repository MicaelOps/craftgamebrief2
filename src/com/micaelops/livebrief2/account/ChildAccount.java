package com.micaelops.livebrief2.account;

public class ChildAccount extends Account{

    private long progress;

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

    @Override
    public Account readFromStringArray(String[] data) {
        return null;
    }

    @Override
    public String writeToString() {
        return null;
    }
}
