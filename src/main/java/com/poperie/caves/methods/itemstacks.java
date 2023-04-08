package com.poperie.caves.methods;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class itemstacks {

    // TODO: Add support for enchantments and item flags
    // TODO: Switch all itemstacks to use this method
    public static ItemStack createItemStack(Material material, int amount, String name, String lore, int DataValue) {
        ItemStack item = new ItemStack(material, amount, (short) DataValue);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> loreList = new ArrayList<>();
        for (String line : lore.split("%nl%")) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }
}
