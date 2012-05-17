package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class VisualizerCore {

	HashSet<PlayerBlock> blocks = new HashSet<PlayerBlock>();
	
	
	public void revertBlock(Player player, Block block){
		for(PlayerBlock pb : getBlocks()){
			
		}
	}
	
	private void saveBlock(Player player, Block block){
		PlayerBlock pbtemp = new PlayerBlock(player, block);
		PlayerBlock pb = new PlayerBlock(player, block);
		
		if(pbtemp.getBlock() == pb.getBlock() && pbtemp.getPlayer() == pb.getPlayer()){
			return;
		}
		blocks.add(pb);
	}
	
	private void saveLocation(Player player, Location loc){
		saveBlock(player, Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc));
	}
	
	private HashSet<PlayerBlock> getBlocks(){
		return blocks;
	}
}
