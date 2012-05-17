package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

public class BeaconVis {

	private Location loc;
	private int height, interval;
	private Player player;
	TheDarkness plugin;
	
	
	HashSet<Block> blocks = new HashSet<Block>();
	
	public BeaconVis(TheDarkness instance, Player player, Location loc){
		this.loc = loc;
		this.player = player;
		this.plugin = instance;
	}
	
	
	public void visualize(Location loc){
		HashSet<Block> blocks = new HashSet<Block>();
		int x= loc.getBlockX();
		int z = loc.getBlockZ();
		
		for(int y = 0; y < 255; y ++){
			blocks.add(Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(x, y, z));
			x ++;
			z ++;
		}
		for(Block b : blocks){
			plugin.visualizerCore.visualizeBlock(player, b.getLocation() , Material.GLOWSTONE);
		}
		
	}
	
	public void visualize(BeaconData beacon){
		visualize(beacon.getLocation());
	}
	
	
	
	
}
