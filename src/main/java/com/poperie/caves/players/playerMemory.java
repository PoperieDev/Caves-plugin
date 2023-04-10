package com.poperie.caves.players;

import static com.poperie.caves.mining.items.itemUtility.getItemMemory;

public class playerMemory {
    private int backPackSize;
    private double xp;
    private String[] backPack;

    private int backPackSlotSize;
    private int[] backPackItemAmounts;

    // Backpack
    public void setBackPackSlotSize(int backPackSlotSize) {
        this.backPackSlotSize = backPackSlotSize;
    }
    public int getBackPackSlotSize() {
        return backPackSlotSize;
    }
    public void setBackPack(String[] backPack) {
        this.backPack = backPack;
    }
    public String[] getBackPack() {
        return backPack;
    }
    public String getBackPackItem(int slot) {
        return backPack[slot];
    }
    public int[] getBackPackItemAmounts() {
        return backPackItemAmounts;
    }
    public int getBackPackItemAmount(int slot) {
        return backPackItemAmounts[slot];
    }
    public void setBackPackItemAmounts(int[] backPackItemAmounts) {
        this.backPackItemAmounts = backPackItemAmounts;
    }

    public void clearBackPack() {
        backPack = null;
        backPackItemAmounts = null;
    }

    public int getBackPackItemsCount() {
        if (backPack == null) {
            return 0;
        }
        return backPack.length;
    }

    public void removeSlotFromBackPack(int slot) {
        String[] newBackPack = new String[backPack.length-1];
        int[] newBackPackItemAmounts = new int[backPackItemAmounts.length-1];

        // Copy old backpack to new backpack
        System.arraycopy(backPack, 0, newBackPack, 0, slot);
        System.arraycopy(backPack, slot+1, newBackPack, slot, backPack.length-slot-1);

        // Copy old backpack item amounts to new backpack item amounts
        System.arraycopy(backPackItemAmounts, 0, newBackPackItemAmounts, 0, slot);
        System.arraycopy(backPackItemAmounts, slot+1, newBackPackItemAmounts, slot, backPackItemAmounts.length-slot-1);

        backPack = newBackPack;
        backPackItemAmounts = newBackPackItemAmounts;
    }

    public void addItemToBackpack(String item) {

        // Check if backpack is full
        if (backPackSize <= getBackPackItemsCount()) {
            return;
        }

        // Check if backpack is empty
        if (backPack == null) {
            backPack = new String[1];
            backPack[0] = item;
            backPackItemAmounts = new int[1];
            backPackItemAmounts[0] = 1;
            return;
        }

        // Check if item is already in backpack
        for (int i = 0; i < backPack.length; i++) {
            if (backPack[i].equals(item)) {
                // Check if backPackSlotSize is already reached, if not, add 1 to backPackSlotSize
                if (backPackItemAmounts[i] < backPackSlotSize) {
                    backPackItemAmounts[i]++;
                    return;
                }
            }
        }

        // Add item to a new slot in the backpack
        String[] newBackPack = new String[backPack.length+1];
        int[] newBackPackItemAmounts = new int[backPackItemAmounts.length+1];

        // Copy old backpack to new backpack
        System.arraycopy(backPack, 0, newBackPack, 0, backPack.length);

        // Copy old backpack item amounts to new backpack item amounts
        System.arraycopy(backPackItemAmounts, 0, newBackPackItemAmounts, 0, backPackItemAmounts.length);

        // Add new item to new backpack
        newBackPack[newBackPack.length-1] = item;
        // Set new item amount to 1
        newBackPackItemAmounts[newBackPackItemAmounts.length-1] = 1;

        // Set new backpack
        backPack = newBackPack;
        // Set new backpack item amounts
        backPackItemAmounts = newBackPackItemAmounts;
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

    public double getBackPackWorth() {
        // Loop through the backpack and add the worth of each item * the amount of items in that slot to the total worth
        double totalWorth = 0;
        if (backPack == null) {
            return totalWorth;
        }
        for (int i = 0; i < backPack.length; i++) {
            totalWorth += getItemMemory(backPack[i]).getWorth() * backPackItemAmounts[i];
        }
        return totalWorth;
    }
}
