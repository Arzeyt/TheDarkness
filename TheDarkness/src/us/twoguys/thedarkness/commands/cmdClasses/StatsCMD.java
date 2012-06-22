package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class StatsCMD {

	TheDarkness p;
	
	public StatsCMD(TheDarkness instance){
		this.p = instance;
	}
	public boolean stats(Player player){
		int level = p.locCheck.getDarknessLevel(player);
		int distance = p.beaconMaster.distanceFromNearestBeacon(player.getLocation());
		try{
			int points = p.beaconPlayerDataMaster.getData((Player)player).getBeaconPoints();
			player.sendMessage("Dark Essence: "+ChatColor.GREEN+points+ChatColor.WHITE+
					"    Darkness Level: "+ChatColor.GREEN+level);
			return true;
		}catch(NullPointerException e){
			player.sendMessage(ChatColor.RED+"You have no dark essence"+ChatColor.WHITE+"" +
					"    Darkness Level: "+ChatColor.GREEN+level);
			return true;
		}
		
	}
	public String toString(){
		return new String("/td stats "+ChatColor.GRAY+" Displays your dark essence.");
		
	}
}
