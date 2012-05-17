package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CopyOfVisualizerCore {

	HashSet<PlayerBlock> blocks = new HashSet<PlayerBlock>();
	
	
	public void revertBlock(Player player, Block block){
		for(PlayerBlock pb : getPlayerData(player)){
			player.sendBlockChange(pb.getBlock().getLocation(), block.getType(), (byte) 0);
		}
	}
	
	public HashSet<PlayerBlock> getPlayerData(Player player){
		HashSet<PlayerBlock> playerBlocks = new HashSet<PlayerBlock>();
		for(PlayerBlock pb : getBlocks()){
			if(pb.getPlayer().getName().equals(player.getName())){
				playerBlocks.add(pb);
			}
		}
		return playerBlocks;
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
