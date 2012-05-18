package us.twoguys.thedarkness.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconStats implements CommandExecutor{

	TheDarkness plugin;

	public BeaconStats(TheDarkness instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be logged in to do that");
			return false;
		}
		try{
			int points = plugin.beaconPlayerDataHandler.getData((Player)sender).getBeaconPoints();
			
			sender.sendMessage("BeaconPoints: "+ChatColor.GREEN+points);
			
			
		}catch(NullPointerException e){
			sender.sendMessage(ChatColor.RED+"You have no beacons, or beacon points");
		}
		
		return true;
		
	}
	

}
