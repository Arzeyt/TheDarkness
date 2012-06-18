package us.twoguys.thedarkness.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconVisionCMD {

	TheDarkness p;
	
	public BeaconVisionCMD(TheDarkness p){
		this.p=p;
	}
	
	public boolean beaconVision(Player player){
		p.visualizerCore.visualizeNearestBeacon(player);
		return true;
	}
	
	public String toString(){
		return new String("/td vision "+ChatColor.GRAY+"Reveals the nearest beacon");
	}
	
}
