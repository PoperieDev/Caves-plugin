package com.poperie.caves.commands.buy;

import com.poperie.caves.methods.guiMethods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.poperie.caves.methods.itemstacks.createItemStack;
import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class buyInventories {
    public static Inventory openBuyMenuMain (Player player) {
        Inventory inventory = player.getServer().createInventory(player, 6 * 9, "§b§lBuy");
        guiMethods.fillBottomRow(inventory, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3));

        inventory.setItem(20, createItemStack(Material.DIAMOND_SWORD, 1, "&b&lRANKS", "&fKøb rank, som giver dig permanente fordele.%nl%&bKlik for at se ranksne", 0));
        inventory.setItem(24, createItemStack(Material.NETHER_STAR, 1, "&b&lPERKS", "&fKøb perks, som giver dig midlertidige fordele.%nl%&bKlik for at se perksne", 0));

        inventory.setItem(36, createItemStack(Material.EMERALD, 1, "&b&lCOINS", "&fDu har &b" + getPlayerMemory(player).getCoins() + " &fcoins", 0));
        return inventory;
    }
}
