package us.twoguys.thedarkness.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.twoguys.thedarkness.TheDarkness;

public class Reload implements CommandExecutor{

	TheDarkness plugin;
	
	public Reload(TheDarkness instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		
		plugin.config.loadConfiguration();
		
		plugin.sendMessage(sender, "Successfully reloaded config!");
		
		return true;
	}
}
