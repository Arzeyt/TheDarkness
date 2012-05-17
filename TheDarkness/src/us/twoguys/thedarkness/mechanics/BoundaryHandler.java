package us.twoguys.thedarkness.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

public class BoundaryHandler {

	TheDarkness plugin;
	
	public BoundaryHandler(TheDarkness instance){
		plugin = instance;
	}
	
	public int distanceFromNearestBeacon(Location loc){
		int distance = 0;
		
		for(BeaconData beacon : plugin.beaconHandler.getBeaconSet()){
			if(beacon.getWorldName().equals(loc.getWorld().getName())){
				int locX = loc.getBlockX();
				int locZ = loc.getBlockZ();
				
				int newDistance = (int) Math.sqrt((beacon.getX()-locX)*(beacon.getX()-locX) + (beacon.getZ()-locZ)*(beacon.getZ()-locZ));
				
				if(newDistance < distance) distance = newDistance;
			}
		}
		return distance;
		
	}
	
	public int getDarknessLevel(Player player){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(player.getName().equals(p.getName())){
				return plugin.config.getLevel(distanceFromNearestBeacon(player.getLocation()));
			}
		}
		return 0;
	}
}
