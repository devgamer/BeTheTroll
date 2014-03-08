package io.github.starman3.beTheTroll;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeTheTroll extends JavaPlugin {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player target = (Bukkit.getServer().getPlayer(args[0]));
		if (target != null) {
			if (cmd.getName().equalsIgnoreCase("burn")) {
				if (args.length != 2) return false;
				if (sender instanceof Player) {
					getLogger().info(sender.getName()+" set "+args[0]+" on fire for "+args[1]+" ticks!");
					sender.sendMessage("You set "+args[0]+" on fire!");
				}
				else getLogger().info("You set "+args[0]+" on fire for "+args[1]+" ticks!");
				target.setFireTicks(Integer.parseInt(args[1]));
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("lightning")) {
				if (args.length != 2) return false;
				if (sender instanceof Player) {
					getLogger().info(sender.getName()+" striked "+args[0]+" with lightning "+args[1]+" times!");
					sender.sendMessage("You striked "+args[0]+" with lightning!");
				}
				else getLogger().info("You striked "+args[0]+" with lightning "+args[1]+" times!");
				target.getWorld().strikeLightning(target.getLocation());
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("tnt")) {
				if (args.length != 3) return false;
				if (sender instanceof Player) {
					getLogger().info(sender.getName()+" blew up "+args[0]+" with "+args[1]+" blocks of TNT from "+args[2]+" blocks high!");
					sender.sendMessage("You blew up "+args[0]+" with TNT!");
				}
				else getLogger().info("You blew up "+args[0]+" with "+args[1]+" blocks of TNT from "+args[2]+" blocks high!");
				Location fallLoc;
				for (int i=0; i < Integer.parseInt(args[1]); i++) {
					fallLoc = target.getLocation();
					fallLoc.setY(fallLoc.getY()+Integer.parseInt(args[2]));
					target.getWorld().spawnEntity(fallLoc, EntityType.PRIMED_TNT);
				}
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("pit")) {
				if (args.length != 3) return false;
				if (sender instanceof Player) {
					getLogger().info(sender.getName()+" dug a pit below "+args[0]+" to the void that was "+args[1]+" blocks long and "+args[2]+" blocks wide!");
					sender.sendMessage("You dug a pit below "+args[0]+" to the void!");
				}
				else getLogger().info("You dug a pit below "+args[0]+" to the void that was "+args[1]+" blocks long and "+args[2]+" blocks wide!");
				Location digLoc = target.getLocation();
				digLoc.setY(digLoc.getBlockY()-1);
				for (int l=0; l < Integer.parseInt(args[1]); l++) {
					digLoc.getBlock().setType(Material.AIR);
				}
				return true;
			}
		}
		sender.sendMessage(args[0]+" is not online!");
		return false;
	}
}
