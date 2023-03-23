package com.micaelops.livebrief2.menu.menus;

import com.micaelops.livebrief2.menu.Menu;


/***
 * As designed in element 010 the Homepage menu is
 * responsible for identifying and authenticate the user
 * whether it is a child or a parent.
 *
 */
public class HomepageMenu implements Menu {

    /**
     * Menu only runs once
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Menu getNewMenu() {
        return null;
    }

    /**
     *
     */
    @Override
    public void process() {

    }
}
