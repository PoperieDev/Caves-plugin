package com.poperie.caves;

import com.poperie.caves.admin.*;
import com.poperie.caves.commands.buy.buyCommand;
import com.poperie.caves.commands.coinsCommand;
import com.poperie.caves.commands.reglerCommand;
import com.poperie.caves.npcs.sell.sellNpcEvents;
import com.poperie.caves.npcs.sell.sellNpcGui;
import com.poperie.caves.players.backpack.backPackEvents;
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

import static com.poperie.caves.mining.blocks.blockLoader.loadBlocks;
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
        Objects.requireNonNull(getCommand("caveblocks")).setExecutor(new blocksCommand());
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new viewBackPackCommand());
        Objects.requireNonNull(getCommand("regler")).setExecutor(new reglerCommand());
        Objects.requireNonNull(getCommand("buy")).setExecutor(new buyCommand());
        Objects.requireNonNull(getCommand("coins")).setExecutor(new coinsCommand());
        Objects.requireNonNull(getCommand("coinsadmin")).setExecutor(new coinsAdminCommand());

        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new playerDataEvents(), this);
        getServer().getPluginManager().registerEvents(new backPackEvents(), this);
        getServer().getPluginManager().registerEvents(new sellNpcGui(), this);
        getServer().getPluginManager().registerEvents(new sellNpcEvents(), this);
        getServer().getPluginManager().registerEvents(new blocksAdminEvents(), this);

        // Load items from items.yml file
        File configFile = new File(getDataFolder().getAbsolutePath() + "/items.yml");
        if (!configFile.exists()) {
            this.saveResource("items.yml", true);
        }

        // Load server data
        serverData.loadServerData();
        loadBlocks();

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Bukkit.getScheduler().runTaskLater(this, () -> createScoreboard(player), 20);
            }
        }

        // Enable tasks
        repeatingTasks.startSavingPlayerData(this);
        repeatingTasks.startBroadcasts(this);

        // Log startup message to console
        getLogger().info("Caves has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Caves has been disabled!");
        serverData.saveServerData();
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
