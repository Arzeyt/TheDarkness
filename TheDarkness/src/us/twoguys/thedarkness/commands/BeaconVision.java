package us.twoguys.thedarkness.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconVision implements CommandExecutor{

	TheDarkness plugin;

	public BeaconVision(TheDarkness instance){
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player == false){
			plugin.logSevere("You must be logged in to do that");
		}
		plugin.visualizerCore.visualizeBeacons((Player)sender);
		return false;
	}
}
