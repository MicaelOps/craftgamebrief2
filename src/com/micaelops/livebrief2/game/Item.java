package com.micaelops.livebrief2.game;

/**
 * Extension of the ItemType class to store items in the
 * child's inventory with amount
 */
public class Item {

    // holds item information
    private final ItemType itemType;

    // holds the amount
    private int amount;


    public Item(ItemType itemType, int amount) {
        this.itemType = itemType;
        this.amount = amount;
    }

    /**
     * Sets the amount
     * @param amount value
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the amount of the item
     * @return item amount value
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets the information about the Item
     * @return itemtype
     */
    public ItemType getItemType() {
        return itemType;
    }
}
