package com.micaelops.livebrief2.account;

public class ChildAccount extends Account{

    private long progress;

    public ChildAccount(String name, String username, int age, long progress) {
        super(name, age, username);
        this.progress = progress;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
