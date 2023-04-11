package com.micaelops.livebrief2.game;

import java.util.Arrays;
import java.util.Random;

/**
 * This class represents all the items that exist
 * inside the game
 *
 * Every item must have a name, progress required to access,
 * and it is optional whether they have components or not
 *
 * The Items with components cannot be obtained by exploring the world and must be crafted.
 */
public enum ItemType {

    WIRE("Wire",0 ),
    PLASTIC("Plastic",0),
    PLASTIC_BAG("Plastic-Bag", 10, new Item(ItemType.PLASTIC, 2));

    final String name;
    final long progressRequired;
    final Item[] components;

    ItemType(String name, long progressRequired, Item... components){
        this.name = name;
        this.progressRequired = progressRequired;
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
     * Get Progress Required for the ItemType
     * @return long value of progress
     */

    public long getProgressRequired() {
        return progressRequired;
    }

    /**
     * Gets all the items that have components
     * @return array with items that have components
     */

    public static ItemType[] getItemsWithComponents(){
        return Arrays.stream(values()).filter(itemType -> itemType.components.length > 0).toArray(ItemType[]::new);
    }

    /**
     * Gets a random ItemType that does not have
     * components with certain progress.
     * @return ItemType
     */

    public static ItemType getRandomItem(long progress){
        ItemType[] noComponentsItems = Arrays.stream(values()).filter(itemType -> itemType.components.length == 0 && itemType.getProgressRequired() <= progress).toArray(ItemType[]::new);
        return noComponentsItems[new Random().nextInt(noComponentsItems.length)];
    }

    /**
     * Gets the ItemType enum by name
     * @param name of the item
     * @return ItemType if exists or else null
     */
    public static ItemType getItemByName(String name){
        return Arrays.stream(values()).filter(itemType -> itemType.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
