package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

import us.twoguys.thedarkness.beacon.BeaconData;

public class BeaconVis {

	private Location loc;
	private int height, interval;
	
	HashSet<Block> blocks = new HashSet<Block>();
	
	public BeaconVis(Location loc){
		this.loc = loc;
	}
	
	public BeaconVis(Location loc, int height, int interval){
		this(loc);
		this.height = height;
		this.interval = interval;
	}
	
	public void visualize(Location loc){
		
	}
	
	public void visualize(BeaconData beacon){
		visualize(beacon.getLocation());
	}
	

	
	
}
