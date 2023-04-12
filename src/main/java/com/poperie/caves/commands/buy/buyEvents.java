package com.poperie.caves.commands.buy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

import static com.poperie.caves.methods.message.messageAllStaff;

public class buyEvents implements Listener {

    private static Map<Player, Boolean> awaitingCoinsChat = new HashMap<>();

    @EventHandler
    public void onBuyChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!awaitingCoinsChat.containsKey(player)) {
            return;
        }

        if (event.getMessage().equals("!annuller")) {
            awaitingCoinsChat.remove(player);
            player.sendMessage("§cDu har annulleret købet af coins");
            return;
        }

        if (event.getMessage().matches("[0-9]+")) {
            int amount = Integer.parseInt(event.getMessage());
            if (amount >= 50) {
                messageAllStaff("&f%nl%&8[ &b&lCAVES &8] &fBuy%nl%&b" + player.getPlayer() + " Vil købe: &b" + amount + " coins%nl%&f");
                awaitingCoinsChat.remove(player);
            } else {
                player.sendMessage("§cDu kan minimum købe 50 coins ad gangen");
            }
        } else {
            player.sendMessage("§cDu skal skrive et tal, prøv igen");
        }
    }

    @EventHandler
    public void onBuyClick(InventoryClickEvent event) {
        if (!(event.getInventory().getTitle().equals("§b§lBuy"))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        if (event.getSlot() == 20) {
            // Ranks

        } else if (event.getSlot() == 24) {
            // Perks
        } else if (event.getSlot() == 36) {
            if (awaitingCoinsChat.containsKey(player)) {
                player.sendMessage("§cDu er allerede igang med at købe coins, skriv §f!annuller §cfor at annullere");
                // TODO: Test if this sound works
                player.playSound(player.getLocation(), "minecraft:note.bass", 1, 1);
                return;
            }
            awaitingCoinsChat.put(player, true);
        }
    }
}