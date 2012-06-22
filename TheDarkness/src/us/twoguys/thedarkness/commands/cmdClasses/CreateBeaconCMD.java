package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class CreateBeaconCMD {

	TheDarkness plugin;
	
	public CreateBeaconCMD(TheDarkness instance){
		this.plugin = instance;
	}
	
	public boolean createBeacon(Player player){
		plugin.beaconListenerMaster.setString(player, "beaconPlace");
		plugin.sendMessage(player, "Select a location to place the beacon");
		return true;
	}
	
	public String toString(){
		return new String("/td create "+ChatColor.GRAY+" Creates a beacon at the selected block");
	}
}
