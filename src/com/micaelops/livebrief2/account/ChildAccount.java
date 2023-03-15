package com.micaelops.livebrief2.account;

public class ChildAccount extends Account{

    private long progress;

    public ChildAccount(String name, String username, int age, AccountType type, long progress) {
        super(name, age, AccountType.CHILD, username);
        this.progress = progress;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
