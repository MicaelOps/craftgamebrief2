package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.account.Account;
import com.micaelops.livebrief2.menu.Menu;

public class ParentGameMenu extends Menu {

    public ParentGameMenu(Account account) {

    }

    @Override
    public void welcome() {
        System.out.println("Welcome!");
    }


    @Override
    public void process() {

    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Menu getNewMenu() {
        return null;
    }
}
