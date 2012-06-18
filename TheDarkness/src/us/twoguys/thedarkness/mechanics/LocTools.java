package us.twoguys.thedarkness.mechanics;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

public class LocTools {

	public TheDarkness plugin;
	
	public LocTools(TheDarkness instance){
		this.plugin = instance;
	}
	
	public Location getRandomGround(Location loc, int distance){
		//plugin.debug("ground loc check*********************");
		Random rand = new Random();
		
		int x = (loc.getBlockX() - distance);
		int xOffset=rand.nextInt(distance*2);
		//plugin.debug("x = "+x+", and xOffset = "+xOffset);
		
		int z = (loc.getBlockZ() - distance);
		int zOffset=rand.nextInt(distance*2);
	//	plugin.debug("z = "+z+", and zOffset = "+zOffset);
		
		Location newLoc = new Location(loc.getWorld(), x+xOffset, loc.getBlockY(), z+zOffset);
		Block block=newLoc.getBlock();
		
		return getGroundBlock(block).getLocation();
		
	}
	/**
	 * 
	 * @param 
	 * @return The nearest ground block to the block passed in in terms of the y axis.
	 */
	public Block getGroundBlock(Block block){
		int highGround = getHighGround(block);
		int highInt = highGround != -1 ? highGround - block.getY() : 1000;
		
		int lowGround = getLowGround(block);
		int lowInt = lowGround != -1 ? block.getY()-lowGround : 1000;
		int y = 0;
		if(highInt < lowInt){
			y = highGround;
		}else{
			y = lowGround;
		}
		//plugin.debug("ground block is at " + block.getX() +" "+y+" "+block.getZ());
		return Bukkit.getServer().getWorld(block.getLocation().getWorld().getName()).getBlockAt(block.getX(), y, block.getZ());
	}
	
	/**
	 * 
	 * @param block
	 * @return returns the y position of the ground block nearest to the passed in block, but above it.
	 */
	public int getHighGround(Block block){
		for( int y = block.getY(); y < 256; y++){
			int ydif = y - block.getY();
			Block b = block.getRelative(0, ydif, 0);
			if(b.getType()!=Material.AIR && b.getRelative(0, 1, 0).getType() == Material.AIR && b.getRelative(0, 2, 0).getType() == Material.AIR){
			//	plugin.debug("high ground block y position is "+y);
				return y;
			}
		}
		//plugin.debug("high ground returned -1");
		return -1;
	}
	/**
	 * 
	 * @param block
	 * @return returns the y position of the ground block nearest to the passed in block, but below it.
	 */
	public int getLowGround(Block block){
		for(int y = block.getY(); 0 < y; y--){
			int ydif = y-block.getY();
			Block b = block.getRelative(0, ydif, 0);
			if(b.getType()!=Material.AIR && b.getRelative(0, 1, 0).getType() == Material.AIR && b.getRelative(0, 2, 0).getType() == Material.AIR){
			//		plugin.debug("low ground block y position is "+y);
					return y;
			}
		}
		//plugin.debug("low ground returned -1");
		return -1;
	}

	public double getDistance(Location loc1, Location loc2){
		double distance = Math.sqrt(Math.pow(loc1.getX()-loc2.getX(), 2) + Math.pow(loc1.getZ()-loc2.getZ(), 2));
	
		return distance;
		
	}
	
	public BeaconData getNearestBeacon(Player player){
		BeaconData nearest=null;
		for(BeaconData beacon : plugin.beaconMaster.getBeacons()){
			if(beacon.getWorldName().equalsIgnoreCase(player.getWorld().getName())){
				if(nearest == null){
					nearest = beacon;
				}else if(getDistance(player.getLocation(), beacon.getLocation()) 
						< getDistance(player.getLocation(), nearest.getLocation())){
					nearest=beacon;
				}
			}
		}
		return nearest;
	}
}
