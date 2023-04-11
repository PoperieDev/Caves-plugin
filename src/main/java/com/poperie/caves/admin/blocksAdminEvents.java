package com.poperie.caves.admin;

import com.poperie.caves.mining.blocks.blockMemory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class blocksAdminEvents implements Listener {

    @EventHandler
    public void onBlockPlaceAdmin(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("caves.admin"))) {
            return;
        }
        String action;
        String group;
        if (blocksCommand.playersEditingBlocks.containsKey(player.getName())) {
            String[] value = blocksCommand.playersEditingBlocks.get(player.getName());

            group = value[0];
            action = value[1];
        } else {
            return;
        }
        // TODO: Finish this event
        if (action.equals("add")) {
            blockMemory.addBlock(group, event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockBreakAdmin(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("caves.admin"))) {
            return;
        }
        String action;
        if (blocksCommand.playersEditingBlocks.containsKey(player.getName())) {
            String[] value = blocksCommand.playersEditingBlocks.get(player.getName());
            action = value[1];
        } else {
            return;
        }
        assert action != null;
        if (action.equals("remove")) {
            blockMemory.removeBlock(event.getBlock().getLocation());
        }
    }
}
