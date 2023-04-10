package com.poperie.caves.admin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class blocksAdminEvents implements Listener {

    @EventHandler
    public void onBlockPlaceAdmin(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("caves.admin"))) {
            return;
        }
        if (blocksCommand.playersEditingBlocks.containsKey(player.getName())) {
            String[] value = blocksCommand.playersEditingBlocks.get(player.getName());
            String group = value[0];
            String action = value[1];
        }

        // TODO: Finish this event

    }
}
