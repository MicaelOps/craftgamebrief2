package com.micaelops.livebrief2.account;

import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.game.ItemType;

import java.util.Arrays;


/**
 * Extension of Account class for the child.
 * Default Inventory size: 27
 *
 * Progress is used to track the XP of the child and
 * if the child has created a World or not.
 * More info about this on ChildGameMenu class
 */

public class ChildAccount extends Account{

    // XP of the child
    private long progress;

    // Inventory of the Child
    private final Item[] inventory;


    public ChildAccount(String username, String password, String name, int age, long progress, Item[] inventory) {
        super(username, password, name, age);
        this.progress = progress;
        this.inventory = inventory;
    }

    public ChildAccount(){
        this.inventory = new Item[27];
    }

    /**
     * Gets the progress of the child
     * @return long value
     */
    public long getProgress() {
        return progress;
    }

    /**
     * Sets new progress
     * @param progress new long value
     */

    public void setProgress(long progress) {

        // Ensures no negative values
        if(progress < 0)
            progress = 0;

        this.progress = progress;
    }

    /**
     * Checks if it is the First time the
     * user Logged in
     * @return true or false
     */

    public boolean isFirstTime(){
        return getProgress() == 0;
    }

    /**
     * Resets child progress and inventory
     */

    public void resetWorldStats(){
        setProgress(0);
        Arrays.fill(inventory, null);
    }

    /**
     * Reads Data from array
     * @param data array containing data
     * @return ChildAccount object
     */

    @Override
    public Account readFromStringArray(String[] data) {

        Item[] inventory = new Item[this.inventory.length];

        for(int i = 0; i < inventory.length; i++) {

            // Empty spaces in the inventory are saved as "null" on the file
            if(!data[i+6].equalsIgnoreCase("null")) {
                String[] itemData = data[i+6].split(" ");
                inventory[i] = new Item(ItemType.getItemByName(itemData[0]), Integer.parseInt(itemData[1]));
            }
        }

        return new ChildAccount(data[2], data[3], data[1], Integer.parseInt(data[4]), Long.parseLong(data[5]), inventory);
    }

    /**
     * Translates data into String array
     * @return data in String array
     */

    @Override
    public String[] writeToStringArray() {

        String[] data = new String[33]; // 5 from account variables + 27 inventory  + 1 progress

        data[0] = "child";
        data[1] = getName();
        data[2] = getUsername();
        data[3] = getPassword();
        data[4] = ""+getAge();
        data[5] = ""+getProgress();

        for(int i = 0; i < inventory.length; i++) {

            // Only saves valid items
            if(inventory[i] != null)
                data[i+6] = inventory[i].getItemType().getName()+" "+inventory[i].getAmount();

        }
        return data;
    }

    /**
     * Gets the child's Inventory
     * @return Items array
     */
    public Item[] getItems() {
        return inventory;
    }

    /**
     * Adds Item to the inventory
     * @param item Item to the inventory
     */

    public void addItem(Item item){

        // First find a slot with the same ItemType
        int slot = findItemSlot(item);

        // Check if we need to find an empty slot for the item
        if(slot == -1){

            int emptySlot = findNearestEmptySlot();

            // Check if the inventory is not full
            if(emptySlot != -1)
                inventory[emptySlot] = item;

        } else
            // Increases the amount of the already existing ItemType
            inventory[slot].setAmount(inventory[slot].getAmount() + item.getAmount());
    }

    /**
     * Removes item from inventory
     * @param item Item to remove
     */

    public void removeItem(Item item) {

        // First find a slot with the same ItemType
        int slot = findItemSlot(item);

        // Check if is valid slot
        if(slot != -1){

            // Check if the amount in the inventory exceeds the amount we need to remove

            if(inventory[slot].getAmount() <= item.getAmount())
                inventory[slot] = null;
            else
                inventory[slot].setAmount(inventory[slot].getAmount() - item.getAmount());
        }
    }

    /**
     * Find slot with the Item
     * @param item Item to find
     * @return integer position of the slot
     */

    public int findItemSlot(Item item) {
        for(int i = 0; i < inventory.length; i++){

            // Basically we are checking if the ItemType exists and if the amount is equal or greater than the item we want to find
            // Example: checking if there is 5 wires on the inventory.

            if(inventory[i] != null && inventory[i].getItemType() == item.getItemType() && inventory[i].getAmount() >=item.getAmount())
                return i;
        }
        return -1;
    }

    /**
     * Find empty slot on the Inventory
     * @return integer position of the slot
     */

    public int findNearestEmptySlot(){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null)
                return i;
        }
        return -1;
    }

}
