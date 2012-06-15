package us.twoguys.thedarkness.beacon;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BeaconData implements Serializable{

	private static final long serialVersionUID = 8032773682609808557L;
	int x, y, z, height, interval;
	String worldName;
	
	public BeaconData(Location loc){
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		
		this.worldName = loc.getWorld().getName();
	}
	
	public BeaconData(Location loc, int height, int interval){
		this(loc);
		this.height = height;
		this.interval = interval;
	}
	public String getWorldName(){
		return this.worldName;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}
	
	public Location getLocation(){
		return new Location(Bukkit.getWorld(worldName), x, y, z);
	}
}
