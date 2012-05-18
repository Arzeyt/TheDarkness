package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class UNUSEDVisualizerCore {

	HashSet<UNUSEDBlockPlayer> blocks = new HashSet<UNUSEDBlockPlayer>();
	
	
	public void revertBlock(Player player, Block block){
		for(UNUSEDBlockPlayer pb : getPlayerData(player)){
			player.sendBlockChange(pb.getBlock().getLocation(), block.getType(), (byte) 0);
		}
	}
	
	public HashSet<UNUSEDBlockPlayer> getPlayerData(Player player){
		HashSet<UNUSEDBlockPlayer> playerBlocks = new HashSet<UNUSEDBlockPlayer>();
		for(UNUSEDBlockPlayer pb : getBlocks()){
			if(pb.getPlayer().getName().equals(player.getName())){
				playerBlocks.add(pb);
			}
		}
		return playerBlocks;
	}
	
	private void saveBlock(Player player, Block block){
		UNUSEDBlockPlayer pbtemp = new UNUSEDBlockPlayer(player, block);
		UNUSEDBlockPlayer pb = new UNUSEDBlockPlayer(player, block);
		
		if(pbtemp.getBlock() == pb.getBlock() && pbtemp.getPlayer() == pb.getPlayer()){
			return;
		}
		blocks.add(pb);
	}
	
	private void saveLocation(Player player, Location loc){
		saveBlock(player, Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc));
	}
	
	private HashSet<UNUSEDBlockPlayer> getBlocks(){
		return blocks;
	}
}
