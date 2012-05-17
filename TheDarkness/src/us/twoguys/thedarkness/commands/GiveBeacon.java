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
		
		if(plugin.beaconPlayerDataHandler.playerDataExists(Bukkit.getPlayer(playerName))){
			BeaconPlayerData beaconPlayerData = plugin.beaconPlayerDataHandler.getData(Bukkit.getPlayer(playerName));
			beaconPlayerData.addBeaconAmount(amount);
			
			sender.sendMessage(ChatColor.GREEN + "You have given " + playerName + " " + amount + " beacons");
		}else{
			BeaconPlayerData beaconPlayerData = new BeaconPlayerData(playerName, 0, amount);
			plugin.beaconPlayerDataHandler.addBeaconPlayerData(beaconPlayerData);
			
			sender.sendMessage(ChatColor.GREEN+"You have given " +playerName+" " + amount + " beacons. This is his first time!");
		}
	
		Bukkit.getServer().getPlayer(playerName).sendMessage(ChatColor.GREEN+"You have recieved "+amount+" beacons");
		return true;

		
		
		
		
		
		
	}

}
