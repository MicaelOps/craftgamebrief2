package com.micaelops.livebrief2.game;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.database.Database;

public class Game {

    private final Account account;
    private final Database database;

    public Game(Account account, Database database, Account account1, Database database1) {
        this.account = account1;
        this.database = database1;
    }



}
