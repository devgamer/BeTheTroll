package com.gmail.jamesnshiffer.bukkitPluginTest;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitPluginTest extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("BukkitPluginTest's onEnable method has been called!");
	}
	@Override
	public void onDisable() {
		getLogger().info("BukkitPluginTest's onDisable method has been called!");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// if "/troll" has been typed in
		if (cmd.getName().equalsIgnoreCase("troll")) {
			// if player has permission (or is op)
			if (sender.hasPermission("BukkitPluginTest.troll")) {
				// checks for correct amount of args
				if (args.length != 3) return false;
				else {
					Player target = (Bukkit.getServer().getPlayer(args[0]));
			        if (target == null) {
			           if (sender instanceof Player) {
			        	   sender.sendMessage(args[0] + " is not online!");
			        	   getLogger().info(sender.getName()+" trolled "+args[0]+" with "+args[1].toLowerCase()+" for "+args[2]+" "+(args[1].equalsIgnoreCase("lightning") || args[1].equalsIgnoreCase("tnt") ? "times" : "ticks")+" but they are offline!");
			           }
			           else getLogger().info("You trolled "+args[0]+" but they are offline!");
			           return true;
			        }
			        boolean targetIsSelf = false;
					if (sender instanceof Player) {
						if (sender.getName().equalsIgnoreCase(target.getName())) targetIsSelf = true;
						sender.sendMessage("You trolled "+(targetIsSelf ? "yourself" : args[0])+"!");
						getLogger().info(sender.getName()+" trolled "+(targetIsSelf ? "themself" : args[0])+" with "+args[1].toLowerCase()+" for "+args[2]+" "+(args[1].equalsIgnoreCase("lightning") || args[1].equalsIgnoreCase("tnt") ? "times" : "ticks"));
					}
					else getLogger().info("You trolled "+args[0]+" with "+args[1].toLowerCase()+" for "+args[2]+" "+(args[1].equalsIgnoreCase("lightning") || args[1].equalsIgnoreCase("tnt") ? "times" : "ticks"));
					if (!targetIsSelf) target.sendMessage(sender.getName()+" trolled you!");
					if (args[1].equalsIgnoreCase("lightning")) {
						for (int i = 0; i < Integer.parseInt(args[2]); i++) {
							target.getWorld().strikeLightning(target.getLocation());
						}
					} else if (args[1].equalsIgnoreCase("fire")) {
						target.setFireTicks(Integer.parseInt(args[2]));
					} else if (args[1].equalsIgnoreCase("tnt")) {
						for (int i = 0; i < Integer.parseInt(args[2]); i++) {
							Location loc = target.getLocation();
							loc.setY(loc.getY()+10);
							World w = loc.getWorld();
							w.spawnEntity(loc, EntityType.PRIMED_TNT);
						}
					} else {
						return false;
					}
					return true;
				}
			}
			else {
				sender.sendMessage("You don't have permission to troll!");
				getLogger().info(sender.getName()+" tried to troll but they don't have permission!");
				return true;
			}
		}
		return false;
	}
}
