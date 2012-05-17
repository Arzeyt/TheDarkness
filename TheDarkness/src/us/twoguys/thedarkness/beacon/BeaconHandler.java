package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconHandler {

	TheDarkness plugin;
	
	HashSet<BeaconData> beacons = new HashSet<BeaconData>();
	
	public BeaconHandler(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * 
	 * @param loc - the location of the new beacon
	 */
	public void addBeacon(BeaconData beacon){
		
		HashSet<BeaconData> temp = beacons;
		
		temp.add(beacon);
		beacons = temp;
	}
	
	public void createBeacon(BeaconData beacon){
		
	}
	
	public void removeBeacon(BeaconData beacon){
		HashSet<BeaconData> temp = beacons;
		temp.remove(beacon);
		
		beacons = temp;
	}
	
	public HashSet<BeaconData> getBeaconSet(){
		return beacons;
	}
	

}
