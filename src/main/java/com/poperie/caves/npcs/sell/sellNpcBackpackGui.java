package com.poperie.caves.npcs.sell;

import com.poperie.caves.methods.guiMethods;
import com.poperie.caves.mining.items.itemMemory;
import com.poperie.caves.players.playerMemory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.mining.items.itemUtility.getItemMemory;
import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class sellNpcBackpackGui {

    public static void openSellNpcBackpackGui(Player player) {
        playerMemory playerMemory = getPlayerMemory(player);

        Inventory inventory = player.getServer().createInventory(player, 6 * 9, "§b§lSælg fra rygsæk");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§f");
        glass.setItemMeta(glassMeta);

        guiMethods.fillInventory(inventory, glass);

        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemMeta blueGlassMeta = blueGlass.getItemMeta();
        blueGlassMeta.setDisplayName("§f");
        blueGlass.setItemMeta(blueGlassMeta);

        guiMethods.fillBottomRow(inventory, blueGlass);

        ItemStack greenGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        ItemMeta greenGlassMeta = blueGlass.getItemMeta();
        greenGlassMeta.setDisplayName("§f");
        greenGlass.setItemMeta(greenGlassMeta);

        if (playerMemory.getBackPackItemsCount() == 0) {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§c§lTom");

            List<String> lore = new ArrayList<>();
            lore.add("§fDin rygsæk er tom!");
            meta.setLore(lore);

            item.setItemMeta(meta);

            inventory.setItem(22, item);
            player.openInventory(inventory);
            return;
        }

        for (int i = 0; i < playerMemory.getBackPackSize(); i++) {
            inventory.setItem(i, greenGlass);
        }

        // Loop through the backpack and add all items to the gui
        for (int i = 0; i < playerMemory.getBackPackItemsCount(); i++) {
            itemMemory itemMemory = getItemMemory(playerMemory.getBackPackItem(i));
            ItemStack item = itemMemory.getItem(playerMemory.getBackPackItemAmount(i));
            ItemMeta meta = item.getItemMeta();

            List<String> lore = new ArrayList<>();
            lore.add("§fVærdi: §b" + getEconomy().format(itemMemory.getWorth() * playerMemory.getBackPackItemAmount(i)));
            lore.add("§bKlik for at sælge denne stack");
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }
        player.openInventory(inventory);
    }
}
