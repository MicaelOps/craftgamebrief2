package com.micaelops.livebrief2.game;

public class Item {

    private final ItemType itemType;

    private int amount;


    public Item(ItemType itemType, int amount) {
        this.itemType = itemType;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
