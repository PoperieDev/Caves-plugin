package com.poperie.caves.npcs.sell;

import com.poperie.caves.methods.guiMethods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.methods.itemstacks.createItemStack;
import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class sellNpcGui implements Listener {

    @EventHandler
    public void onNpcClick(org.bukkit.event.player.PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked().getCustomName().equals("§bSælg"))) {
            return;
        }
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), "mob.villager.yes", 1, 1);

        // TODO: Add code to sell items

        /*   Open a gui with 3 buttons
        / 1. Sell all items in backpack
        / 2. Sell all items of a specific type from backpack
        / 3. Sell item from backpack
        */

        Inventory inventory = player.getServer().createInventory(player, 5*9, "§b§lSælg");

        guiMethods.fillTopAndBottom(inventory, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3));

        // Sell all items button

        ItemStack item = createItemStack(Material.EMERALD_BLOCK, 1, "&b&lSælg alt", "&fVærdi af din ryksæk: &b" + getEconomy().format(getPlayerMemory(player).getBackPackWorth()) + "%nl%&bKlik for at sælge alt", 1);

        inventory.setItem(20, item);
        // TODO Implement sellAllOfType button
        inventory.setItem(22, guiMethods.getButton("sellAllOfType"));

        inventory.setItem(24, guiMethods.getButton("sellItem"));

        player.openInventory(inventory);
    }

}
