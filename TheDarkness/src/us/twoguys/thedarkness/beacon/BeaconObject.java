package us.twoguys.thedarkness.beacon;

import java.io.Serializable;

import org.bukkit.Location;

public class BeaconObject implements Serializable{

	private static final long serialVersionUID = 8032773682609808557L;
	int x, y, z;
	String worldName;
	
	public BeaconObject(Location loc){
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		
		this.worldName = loc.getWorld().getName();
	}
}
