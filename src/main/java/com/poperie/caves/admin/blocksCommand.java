package com.poperie.caves.admin;

import com.poperie.caves.mining.blocks.blockMemory;
import com.poperie.caves.mining.items.itemMemory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static com.poperie.caves.mining.items.itemGroups.getGroup;
import static com.poperie.caves.mining.items.itemUtility.getItemMemory;

public class blocksCommand implements CommandExecutor {

    static HashMap<String, String[]> playersEditingBlocks = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Finish the blocks command
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("/caveblocks start <group>");
            player.sendMessage("/caveblocks stop");
            player.sendMessage("/caveblocks remove");
            player.sendMessage("/caveblocks showblocks (DEBUG ONLY!)");
            player.sendMessage("/caveblocks reloadblocks (DEBUG ONLY!)");
            return true;
        }

        if (args[0].equals("reloadblocks")) {
            String[] groups = blockMemory.getGroups();
            Location[] locations = blockMemory.getLocations();

            for (int i = 0; i < groups.length; i++) {
                String group = groups[i];
                Location location = locations[i];

                // Get a random item from the group
                List<String> itemsInGroup = getGroup(group);
                int random = (int) (Math.random() * itemsInGroup.size());
                String item = itemsInGroup.get(random);
                itemMemory itemMemory = getItemMemory(item);
                Block block = location.getBlock();
                block.setType(itemMemory.getBlock());
            }
        }

        if (args[0].equals("showblocks")) {
            player.sendMessage("§aShowing blocks...");
            String[] groups = blockMemory.getGroups();
            Location[] locations = blockMemory.getLocations();

            for (int i = 0; i < groups.length; i++) {
                String group = groups[i];
                Location location = locations[i];

                // Get a random item from the group
                List<String> itemsInGroup = getGroup(group);
                Block block = location.getBlock();
                block.setType(Material.SPONGE);
            }
        }

        if (args[0].equals("start")) {
            if (args.length == 1) {
                player.sendMessage("/caveblocks start <group>");
                return true;
            }
            playersEditingBlocks.remove(player.getName());

            String group = args[1];
            if (getGroup(group) == null) {
                player.sendMessage("§cThat group does not exist");
                return true;
            }

            String[] groupBlocks = new String[2];
            groupBlocks[0] = group;
            groupBlocks[1] = "add";

            player.sendMessage("§aYou are now editing the group: " + group);

            playersEditingBlocks.put(player.getName(), groupBlocks);
        }

        if (args[0].equals("stop")) {
            playersEditingBlocks.remove(player.getName());
            player.sendMessage("§aYou are no longer editing blocks");
        }

        if (args[0].equals("remove")) {
            playersEditingBlocks.remove(player.getName());

            String[] groupBlocks = new String[2];
            groupBlocks[0] = "null";
            groupBlocks[1] = "remove";

            player.sendMessage("§aYou are now removing blocks");

            playersEditingBlocks.put(player.getName(), groupBlocks);
        }

        return true;
    }
}
