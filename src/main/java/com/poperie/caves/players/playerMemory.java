package com.poperie.caves.players;

import org.bukkit.inventory.ItemStack;

public class playerMemory {
    private int backPackSize;
    private double xp;
    private ItemStack[] backPack;

    // Backpack
    public void setBackPack(ItemStack[] backPack) {
        this.backPack = backPack;
    }
    public ItemStack[] getBackPack() {
        return backPack;
    }
    public ItemStack getBackPackItem(int slot) {
        return backPack[slot];
    }
    public void clearBackPack() {
        backPack = null;
    }

    public int getBackPackItemsCount() {
        if (backPack == null) {
            return 0;
        }
        return backPack.length;
    }

    public void addItemToBackpack(ItemStack item) {

        if (backPackSize <= getBackPackItemsCount()) {
            return;
        }

        if (backPack == null) {
            backPack = new ItemStack[1];
            backPack[0] = item;
            return;
        }

        ItemStack[] newBackPack = new ItemStack[backPack.length + 1];
        for (int i = 0; i < backPack.length; i++) {
            newBackPack[i] = backPack[i];
        }
        newBackPack[backPack.length] = item;
        this.backPack = newBackPack;
    }

    public void setBackPackSize(int backPackSize) {
        this.backPackSize = backPackSize;
    }
    public int getBackPackSize () {
        return backPackSize;
    }

    // Xp
    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getXp () {
        return xp;
    }
}
