package com.poperie.caves.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.poperie.caves.Caves.getEconomy;

public class itemMemory {

    String name;
    Material material;
    boolean shiny;
    int worth;

    // Setter and getter methods for each variable
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public Material getMaterial() {
        return material;
    }
    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }
    public boolean getShiny() {
        return shiny;
    }
    public void setWorth(int worth) {
        this.worth = worth;
    }
    public int getWorth() {
        return worth;
    }

    public ItemStack getItem () {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> loreList = new ArrayList<>();
        loreList.add(" ");
        loreList.add("§fVærdi: §b" + getEconomy().format(worth));
        meta.setLore(loreList);

        // Check if the item is shiny
        if (shiny) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);

        return item;
    }
}
