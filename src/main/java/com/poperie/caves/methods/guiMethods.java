package com.poperie.caves.methods;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class guiMethods {

    public static void fillBottomRow(Inventory inventory, ItemStack item) {

        int inventorySize = inventory.getSize();

        for (int i = inventorySize-9; i < 54; i++) {
            inventory.setItem(i, item);
        }
    }
}
