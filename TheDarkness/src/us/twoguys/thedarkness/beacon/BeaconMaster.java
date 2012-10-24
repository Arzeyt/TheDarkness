package us.twoguys.thedarkness.beacon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.visualization.BeaconVis;

public class BeaconMaster {

	public TheDarkness plugin;
	
	HashMap<Player, HashSet<Location>> effilist = new HashMap<Player, HashSet<Location>>();
	HashSet<BeaconData> beacons = new HashSet<BeaconData>();
	
	
	public BeaconMaster(TheDarkness instance){
		plugin=instance;
	}
	
	public void setupEffilist(Player player){
		HashSet<Location> beaconLocs = new HashSet<Location>();
		ArrayList<Location> tempLocs = new ArrayList<Location>();
		
		int d = 123456789;
		for(Location loc : getBeaconLocations()){
			int temp = (int) plugin.locTools.getDistance(player.getLocation(), loc);
			if(temp<d){
				tempLocs.add(loc);
				d = temp;
			}
			
		}
	}
	
	public HashSet<Location> getBeaconLocations(){
		HashSet<Location> beaconLocations = new HashSet<Location>();
		for(BeaconData bd : getBeacons()){
			beaconLocations.add(bd.getLocation());
		}
		return beaconLocations;
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
		//plugin.debug("Distance to nearest Beacon: " + distance);
		return distance;
	}
	
	public int getDarknessLevel(Player player){
		if(plugin.shield!=null && plugin.shieldAPI.isInRegion(player)){
			return 0;
		}
		return plugin.config.getLevel(distanceFromNearestBeacon(player.getLocation()));
	}
	
	public boolean worldHasBeacon(World world){
		for(BeaconData beacon: beacons){
			if(beacon.getWorldName().equalsIgnoreCase(world.getName())){
				return true;
			}
		}
		return false;
	}

	public BeaconData getNearestBeacon(Location loc){
		int distance = 123456789;
		BeaconData beaconData = null;
		
		for(BeaconData beacon : plugin.beaconMaster.getBeacons()){
			if(beacon.getWorldName().equals(loc.getWorld().getName())){
				int temp = (int) plugin.locTools.getDistance(loc, beacon.getLocation());
				
				if(temp < distance){
					distance = temp;
					beaconData = beacon;
				}
			}
		}
		return beaconData;
	}

}
