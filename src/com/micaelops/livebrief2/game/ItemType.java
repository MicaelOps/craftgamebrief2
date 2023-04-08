package com.micaelops.livebrief2.game;

import java.util.Arrays;

/**
 * This class represents all the items that exist
 * inside the game
 *
 * Every item must have a name, and it is optional whether they have components or not
 * The Items with components cannot be picked up by the child exploring the world and must be crafted.
 */
public enum ItemType {

    WIRE("Wire");

    String name;
    Item[] components;

    ItemType(String name, Item... components){
        this.name = name;
        this.components = components;
    }

    /**
     * Gets the components the item is made of
     * @return array of components
     */
    public Item[] getComponents() {
        return components;
    }

    /**
     * Gets the name of the item
     * @return string with name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets all the items that have components
     * @return array with items that have components
     */
    public static ItemType[] getItemsWithComponents(){
        return (ItemType[]) Arrays.stream(values()).filter(itemType -> itemType.components.length > 0).toArray();
    }

    /**
     * Gets the ItemType enum by name
     * @param name of the item
     * @return ItemType (if exists)
     */
    public static ItemType getItemByName(String name){
        return Arrays.stream(values()).filter(itemType -> itemType.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
