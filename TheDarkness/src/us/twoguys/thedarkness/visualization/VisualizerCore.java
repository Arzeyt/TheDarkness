package us.twoguys.thedarkness.visualization;

import java.util.HashSet;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class VisualizerCore {

	HashSet<PlayerBlock> blocks = new HashSet<PlayerBlock>();
	HashSet<Chunk> chunks = new HashSet<Chunk>();
	
	TheDarkness plugin;
	
	public VisualizerCore(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void visualizeBlock(Player player, Location loc, Material material){
		saveChunk(loc.getChunk());
		player.sendBlockChange(loc, material, (byte) 0);
	}
	
	public void revertChunk(Chunk chunk){
		chunk.getWorld().refreshChunk(chunk.getX(), chunk.getZ());
	}
	
	private void saveChunk(Chunk chunk){
		for(Chunk savedChunk : getChunkSet()){
			if(savedChunk == chunk) return;
		}
		HashSet<Chunk> temp = new HashSet<Chunk>();
		temp.add(chunk);
		chunks = temp;
	}
	
	private HashSet<Chunk> getChunkSet(){
		return chunks;
	}
	
	/*
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
	*/
}