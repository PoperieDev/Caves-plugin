package com.poperie.caves;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import static com.poperie.caves.methods.broadcast.broadcastMessage;
import static com.poperie.caves.players.playerUtility.savePlayerData;
import static org.bukkit.Bukkit.getServer;

public class repeatingTasks {

    public static void startSavingPlayerData(Plugin plugin) {

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {
                savePlayerData(player, false);
            }
            Bukkit.broadcastMessage("§7Alt spillerdata er blevet gemt!");

        }, 20L * 60 * 5, 20L * 60 * 5);
    }

    static int broadcastNumber = 0;
    public static void startBroadcasts(Plugin plugin) {

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {

            if (Math.floorMod(broadcastNumber, 5) == 0)
                broadcastMessage("%nl%&8&l[ &b&lCAVES &8&l]%nl%&7&l» &fHusk at joine vores discord med: &b/discord%nl%&f");
            else if (Math.floorMod(broadcastNumber, 5) == 1)
                broadcastMessage("%nl%&8&l[ &b&lCAVES &8&l]%nl%&7&l» &fTag et kig i vores butik med: &b/buy%nl%&f");
            // TODO: Create buy command
            else if (Math.floorMod(broadcastNumber, 5) == 2) {
                broadcastMessage("%nl%&8&l[ &b&lCAVES &8&l]%nl%&7&l» &fLæs serverens regler med: &b/regler%nl%&f");
                // TODO: Create rules command
            } else if (Math.floorMod(broadcastNumber, 5) == 3) {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    String message = ChatColor.translateAlternateColorCodes('&', "&7&l» &fHar du ideer til serveren?: &b&nVores GitHub");
                    String url = "https://github.com/PoperieDev/Caves-plugin";

                    TextComponent link = new TextComponent(message);
                    link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));

                    TextComponent baseMessage = new TextComponent("");
                    baseMessage.addExtra(link);
                    player.sendMessage("§f");
                    player.sendMessage("§8§l[ §b§lCAVES §8§l]");
                    player.spigot().sendMessage(baseMessage);
                    player.sendMessage("§f");
                }
            } else {
                broadcastMessage("%nl%&8&l[ &b&lCAVES &8&l]%nl%&7&l» &fKan du ikke finde hjem?: &b/spawn%nl%&f");
            }

            broadcastNumber++;

        }, 20L * 60 * 3, 20L * 60 * 3);
    }

}
