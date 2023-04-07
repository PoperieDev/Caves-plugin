package com.poperie.caves.methods;

import com.poperie.caves.players.playerMemory;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.poperie.caves.Caves.getEconomy;

public class guiMethods {

    public static void fillBottomRow(Inventory inventory, ItemStack item) {

        int inventorySize = inventory.getSize();

        for (int i = inventorySize-9; i < inventorySize; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void fillTopRow(Inventory inventory, ItemStack item) {

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
        }
    }

    public static void fillInventory(Inventory inventory, ItemStack item) {

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, item);
        }
    }

    public static void fillTopAndBottom(Inventory inventory, ItemStack item) {
        fillTopRow(inventory, item);
        fillBottomRow(inventory, item);
    }

    // TODO: Remove the playerMemory parameter
    public static ItemStack getButton(String buttonName, playerMemory playerMemory) {

        switch (buttonName) {
            case "reload": {
                ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§b§lGenindlæs");

                // Lore
                List<String> lore = new ArrayList<>();
                lore.add("§7Genindlæs menu");
                lore.add("§bKlik for at genindlæse menuen");
                meta.setLore(lore);

                item.setItemMeta(meta);
                return item;

            }
            case "sellAll": {

                ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§b§lSælg alt");

                // Lore
                List<String> lore = new ArrayList<>();
                lore.add("§fSælg alt for: §b" + getEconomy().format(playerMemory.getBackPackWorth()));
                lore.add("§bKlik for at sælge alt");
                meta.setLore(lore);

                item.setItemMeta(meta);
                return item;

            }
            case "sellAllOfType": {

                ItemStack item = new ItemStack(Material.EMERALD);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§b§lSælg alt af en type");

                // Lore
                List<String> lore = new ArrayList<>();
                lore.add("§fSælg alt af en type");
                lore.add("§bKlik for at se de typer du kan sælge");
                meta.setLore(lore);

                item.setItemMeta(meta);
                return item;

            }
            case "sellItem": {

                ItemStack item = new ItemStack(Material.CHEST);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§b§lSælg fra rygsæk");

                // Lore
                List<String> lore = new ArrayList<>();
                lore.add("§fSælg fra backpack");
                lore.add("§bKlik for at åbne din rygsæk");
                meta.setLore(lore);

                item.setItemMeta(meta);
                return item;

            }
            case "sellAllConfirm": {

                ItemStack item = new ItemStack(Material.WOOL, 1, (short) 5);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§a§lBekræft");

                // Lore
                List<String> lore = new ArrayList<>();
                lore.add("§fBekræft at du vil sælge alt i din ryksæk");
                lore.add("§bKlik for at sælge alt");
                meta.setLore(lore);

                item.setItemMeta(meta);
                return item;
            }
            default:
                throw new IllegalArgumentException("Button name is not valid");
        }
    }
}
