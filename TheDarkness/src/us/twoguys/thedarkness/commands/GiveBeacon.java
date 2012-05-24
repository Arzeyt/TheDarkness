package us.twoguys.thedarkness.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconPlayerData;

public class GiveBeacon implements CommandExecutor{

	TheDarkness plugin;
	
	public GiveBeacon(TheDarkness instance){
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args[0] == null){
			return false;
			
		}else if(args[1] == null){
			return false;
		}
		
		String playerName = args[0];
		int amount = Integer.parseInt(args[1]);
		
		BeaconPlayerData beaconPlayerData = plugin.beaconPlayerDataMaster.getData(Bukkit.getPlayer(playerName));
		beaconPlayerData.incrementPoints(amount);
		
		plugin.sendMessage(sender, ChatColor.GREEN + "You have given " + playerName + " " + amount + " beaconPoints");

	
		Bukkit.getServer().getPlayer(playerName).sendMessage(ChatColor.GREEN+"You have recieved "+amount+" beacons");
		return true;
		
		
	}

}
