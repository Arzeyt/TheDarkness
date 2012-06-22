package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconVisionCMD {

	TheDarkness p;
	
	public BeaconVisionCMD(TheDarkness p){
		this.p=p;
	}
	
	public boolean beaconVision(Player player){
		if(p.comandMaster.containsPlayer(player)==false){
			p.comandMaster.setString(player, "visualize");
		}else if(p.comandMaster.getString(player).equalsIgnoreCase("visualize")){
			p.comandMaster.setString(player, "devisualize");
		}else if(p.comandMaster.getString(player).equalsIgnoreCase("devisualize")){
			p.comandMaster.setString(player, "visualize");
		}
		if(p.beaconMaster.worldHasBeacon(player.getWorld())== false){
			p.sendMessage(player, ChatColor.RED+"The are no beacons in this world");
		}else{
			if(p.comandMaster.getString(player).equalsIgnoreCase("visualize")){
				p.visualizerCore.visualizeNearestBeacon(player);
			}else if(p.comandMaster.getString(player).equalsIgnoreCase("devisualize")){
				p.visualizerCore.revertChunk(player, p.beaconMaster.getNearestBeacon(player.getLocation()).getLocation());
			}
		}
		return true;
	}
	
	public String toString(){
		return new String("/td vision "+ChatColor.GRAY+"Reveals the nearest beacon");
	}
	
}
