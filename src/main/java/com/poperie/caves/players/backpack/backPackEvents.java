package com.poperie.caves.players.backpack;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class backPackEvents implements Listener {
    // TODO: Create a menu with a backpack button
    @EventHandler
    public void onBackPackInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(event.getInventory().getTitle().equals("§b§lRygsæk"))) {
            return;
        }

        if (event.getSlot() == 6*9-4) {
            player.playSound(player.getLocation(), "minecraft:random.click", 1, 1);
        }

        event.setCancelled(true);
    }
}
