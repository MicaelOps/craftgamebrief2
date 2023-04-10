package com.micaelops.livebrief2.account;

import com.micaelops.livebrief2.game.Item;
import com.micaelops.livebrief2.game.ItemType;


public class ChildAccount extends Account{

    private long progress;
    private final Item[] inventory;

    public ChildAccount(String username, String password, String name, int age, long progress, Item[] inventory) {
        super(username, password, name, age);
        this.progress = progress;
        this.inventory = inventory;
    }

    public ChildAccount(){
        this.inventory = new Item[27];
    }

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

        Item[] inventory = new Item[this.inventory.length];

        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] != null) {
                String[] itemData = data[i+6].split(" ");
                inventory[i] = new Item(ItemType.getItemByName(itemData[0]), Integer.parseInt(itemData[1]));
            }
        }

        return new ChildAccount(data[2], data[3], data[1], Integer.parseInt(data[4]), Long.parseLong(data[5]), inventory);
    }

    @Override
    public String[] writeToStringArray() {

        String[] data = new String[14]; // 5 from account variables + accountType and 10 from children

        data[0] = "parent";
        data[1] = getName();
        data[2] = getUsername();
        data[3] = getPassword();
        data[4] = ""+getAge();
        data[5] = ""+getProgress();

        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] != null)
                data[i+6] = inventory[i].getItemType().getName()+" "+inventory[i].getAmount();
        }
        return data;
    }

    public Item[] getItems() {
        return inventory;
    }

    public void addItem(Item item){
        int slot = findItemSlot(item);

        if(slot == -1){

            int emptySlot = findNearestEmptySlot();

            if(emptySlot != -1)
                inventory[emptySlot] = item;

        } else
            inventory[slot].setAmount(inventory[slot].getAmount() + item.getAmount());
    }

    public void removeItem(Item item) {

        int slot = findItemSlot(item);

        if(slot != -1){

            if(inventory[slot].getAmount() <= item.getAmount())
                inventory[slot] = null;
            else
                inventory[slot].setAmount(inventory[slot].getAmount() - item.getAmount());
        }
    }

    public int findItemSlot(Item item) {
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] != null && inventory[i].getItemType() == item.getItemType())
                return i;
        }
        return -1;
    }

    public int findNearestEmptySlot(){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null)
                return i;
        }
        return -1;
    }

}
