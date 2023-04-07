package com.poperie.caves;

import com.poperie.caves.items.loadItems;
import com.poperie.caves.npcs.sell.sellNpcEvents;
import com.poperie.caves.npcs.sell.sellNpcGui;
import com.poperie.caves.players.backpack.backPackEvents;
import com.poperie.caves.players.backpack.viewBackPackCommand;
import com.poperie.caves.players.dataCommand;
import com.poperie.caves.players.playerDataEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

import static com.poperie.caves.scoreboard.createScoreboard;

public final class Caves extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        if (!setupEconomy() ) {
            getLogger().severe("[%s] - Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register the commands
        Objects.requireNonNull(getCommand("cavesdata")).setExecutor(new dataCommand());
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new viewBackPackCommand());

        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new playerDataEvents(), this);
        getServer().getPluginManager().registerEvents(new backPackEvents(), this);
        getServer().getPluginManager().registerEvents(new sellNpcGui(), this);
        getServer().getPluginManager().registerEvents(new sellNpcEvents(), this);

        // Load items from items.yml file
        File configFile = new File(getDataFolder().getAbsolutePath() + "/items.yml");
        if (!configFile.exists()) {
            this.saveResource("items.yml", true);
        }

        // Load items from items.yml file
        loadItems.loadAllItems();

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Bukkit.getScheduler().runTaskLater(this, () -> createScoreboard(player), 20);
            }
        }

        // Log startup message to console
        getLogger().info("Caves has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Run the method after 1 second
        Bukkit.getScheduler().runTaskLater(this, () -> createScoreboard(event.getPlayer()), 20);
    }

    private static Economy econ = null;

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
