package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconHandler {

	TheDarkness plugin;
	
	HashSet<Beacon> beacons = new HashSet<Beacon>();
	
	public BeaconHandler(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * 
	 * @param loc - the location of the new beacon
	 */
	public void addBeacon(Beacon beacon){
		
		HashSet<Beacon> temp = beacons;
		
		temp.add(beacon);
		beacons = temp;
	}
	
	public void removeBeacon(Beacon beacon){
		HashSet<Beacon> temp = beacons;
		temp.remove(beacon);
		
		beacons = temp;
	}
	
	public HashSet<Beacon> getBeaconSet(){
		return beacons;
	}
	
}
