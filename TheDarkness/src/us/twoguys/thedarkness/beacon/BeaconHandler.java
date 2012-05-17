package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconHandler {

	TheDarkness plugin;
	
	HashSet<BeaconObject> beacons = new HashSet<BeaconObject>();
	
	public BeaconHandler(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * 
	 * @param loc - the location of the new beacon
	 */
	public void addBeacon(BeaconObject beacon){
		
		HashSet<BeaconObject> temp = beacons;
		
		temp.add(beacon);
		beacons = temp;
	}
	
	public void removeBeacon(BeaconObject beacon){
		HashSet<BeaconObject> temp = beacons;
		temp.remove(beacon);
		
		beacons = temp;
	}
	
	public HashSet<BeaconObject> getBeaconSet(){
		return beacons;
	}
	

}
