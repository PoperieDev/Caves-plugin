package com.poperie.caves.npcs.sell;

import com.poperie.caves.Caves;
import com.poperie.caves.methods.guiMethods;
import com.poperie.caves.mining.items.itemMemory;
import com.poperie.caves.players.playerMemory;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.mining.items.itemUtility.getItemMemory;
import static com.poperie.caves.npcs.sell.sellNpcBackpackGui.openSellNpcBackpackGui;
import static com.poperie.caves.players.playerUtility.getPlayerMemory;
import static com.poperie.caves.scoreboard.createScoreboard;

public class sellNpcEvents implements Listener {

    @EventHandler
    public void onSellBackPackGuiClick(InventoryClickEvent event) {
        if (!(event.getInventory().getName().equals("§b§lSælg fra rygsæk"))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        playerMemory playerMemory = getPlayerMemory(player);

        if (event.getSlot() < playerMemory.getBackPackItemsCount()) {
            Economy economy = getEconomy();
            itemMemory itemMemory = getItemMemory(playerMemory.getBackPackItem(event.getSlot()));
            int worth = itemMemory.getWorth() * playerMemory.getBackPackItemAmount(event.getSlot());
            player.playSound(player.getLocation(), "minecraft:random.orb", 1, 1);
            player.sendMessage("§fDu solgte §b" + playerMemory.getBackPackItemAmount(event.getSlot()) + "§f " + ChatColor.translateAlternateColorCodes('&', itemMemory.getName()) + "§f for §b" + economy.format(worth) + "§f!");
            playerMemory.removeSlotFromBackPack(event.getSlot());
            economy.depositPlayer(player, worth);
            createScoreboard(player);
            openSellNpcBackpackGui(player);
        }
    }

    @EventHandler
    public void onSellGuiClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (!(event.getInventory().getName().equals("§b§lSælg"))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        playerMemory playerMemory = getPlayerMemory(player);
        double worth = playerMemory.getBackPackWorth();

        Inventory inventory = event.getInventory();

        if (event.getSlot() == 24) {
            if (worth == 0) {
                player.playSound(player.getLocation(), "minecraft:note.bass", 1, 1);
                player.sendMessage("§cDu har ingen ting i din rygsæk!");
                return;
            }
            openSellNpcBackpackGui(player);
            return;
        }

        if (event.getSlot() == 20) {
            if (worth == 0) {
                player.playSound(player.getLocation(), "minecraft:note.bass", 1, 1);
                player.sendMessage("§cDu har ingen ting i din rygsæk!");
                return;
            }
            if (inventory.getItem(event.getSlot()).getType() == Material.EMERALD_BLOCK) {
                inventory.setItem(event.getSlot(), guiMethods.getButton("sellAllConfirm"));
            } else {
                Plugin plugin = Caves.getPlugin(Caves.class);

                Economy economy = getEconomy();
                economy.depositPlayer(player, worth);

                playerMemory.clearBackPack();

                // Update the scoreboard
                createScoreboard(player);

                player.closeInventory();

                for (int i = 1; i < 4; i++) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> player.playSound(player.getLocation(), "minecraft:random.pop", 1, 1), i*10);
                }

                // Send a message
                player.sendMessage("§fDu har solgt alt i din rygsæk for §b" + economy.format(worth) + "§f!");
            }
        }
    }
}
