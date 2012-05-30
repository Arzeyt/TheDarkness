package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import net.minecraft.server.Packet4UpdateTime;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

public class BeaconVis {

	private Location loc;
	private int height = 255;
	private int interval = 3;
	private Player player;
	TheDarkness plugin;
	
	
	HashSet<Block> blocks = new HashSet<Block>();
	
	public BeaconVis(TheDarkness instance, Player player, Location loc){
		this.loc = loc;
		this.player = player;
		this.plugin = instance;
	}
	
	public void visualize(){
		visualize(loc);
	}
	
	public void visualize(Location loc){
		HashSet<Block> blocks = new HashSet<Block>();
		int x= loc.getBlockX();
		int z = loc.getBlockZ();
		int y = 0;
		Block block;
		
		while(y < height){ //construct blocks list
				block = loc.getWorld().getBlockAt(x, y, z);
				blocks.add(block);
				y = y + interval;
			
		}
		for(Block b : blocks){ // paste list
			plugin.visualizerCore.visualizeBlock(player, b.getLocation() , Material.GLOWSTONE);
		}
		
	}
	
	public void visualize(BeaconData beacon){
		visualize(beacon.getLocation());
	}
	
	
	
	
}
