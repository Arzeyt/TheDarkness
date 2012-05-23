package us.twoguys.thedarkness.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

/**
 * 
 * @author Nick
 * @description This class will not be used in release.
 */
public class CreateBeacon implements CommandExecutor {

	TheDarkness plugin;
	
	public CreateBeacon(TheDarkness instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player == false){
			plugin.log("You must be logged in to do that");
			return false;
		}
		Player player = (Player)sender;
		BeaconData bd = new BeaconData(player.getLocation());
		plugin.beaconMaster.createBeacon(player, bd);
		return true;
		
		
	}

}
