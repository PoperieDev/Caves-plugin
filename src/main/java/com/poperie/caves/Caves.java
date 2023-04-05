package com.poperie.caves;

import com.poperie.caves.players.playerDataEvents;
import com.poperie.caves.players.playerUtility;
import com.poperie.caves.players.setDataCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

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
        Objects.requireNonNull(getCommand("cavesdata")).setExecutor(new setDataCommand());

        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new playerDataEvents(), this);

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
