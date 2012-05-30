package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.visualization.BeaconVis;

public class BeaconMaster {

	public TheDarkness plugin;
	
	HashSet<BeaconData> beacons = new HashSet<BeaconData>();
	
	public BeaconMaster(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * Adds a beacon object to the master hash set.
	 * @param beacon - the beacon object to add to the master
	 */
	public void addBeacon(BeaconData beacon){
		for(BeaconData b : getBeacons()){
			if(b.getLocation() == beacon.getLocation())return;
		}
		HashSet<BeaconData> temp = beacons;
		
		temp.add(beacon);
		beacons = temp;
	}
	public void removeBeacon(BeaconData beacon){
		HashSet<BeaconData> temp = beacons;
		temp.remove(beacon);
		
		beacons = temp;
	}
	
	/**
	 * Adds a beacon to the master hash set, subtracts the required points for its creation, and visualizes the 
	 * beacon for all nearby players 
	 *
	 */
	public void createBeacon(Player player, BeaconData beacon){
		if(plugin.beaconPlayerDataMaster.subtractPoints(player, plugin.config.getBeaconCost())==false){
			plugin.sendMessage(player, "Not enough points");
			return;
		}
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(plugin.locTools.getDistance(beacon.getLocation(), p.getLocation()) <= Bukkit.getViewDistance()){
				BeaconVis bv = new BeaconVis(plugin, p, beacon.getLocation());
				bv.visualize();
			}
		}
		addBeacon(beacon);
	}

	public HashSet<BeaconData> getBeacons(){
		return beacons;
	}
	
	public int distanceFromNearestBeacon(Location loc){
		int distance = 123456789;
		
		for(BeaconData beacon : plugin.beaconMaster.getBeacons()){
			if(beacon.getWorldName().equals(loc.getWorld().getName())){
				
				int newDistance = (int) plugin.locTools.getDistance(loc, beacon.getLocation());
				
				if(distance > newDistance) 
					distance = newDistance;
				
			}
		}
		
		plugin.debug("Distance to nearest Beacon: " + distance);
		
		return distance;
		
	}
	
	public int getDarknessLevel(Player player){
		return plugin.config.getLevel(distanceFromNearestBeacon(player.getLocation()));
	}
	

}
