package us.twoguys.thedarkness.visualization;

import java.util.HashSet;
import java.util.List;

import net.minecraft.server.ChunkCoordIntPair;
import net.minecraft.server.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;
import us.twoguys.thedarkness.visualization.ChunkPlayer;

public class VisualizerCore {

	HashSet<ChunkPlayer> chunks = new HashSet<ChunkPlayer>();
	
	TheDarkness plugin;
	
	public VisualizerCore(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void visualizeBlock(Player player, Location loc, Material material){
		saveChunk(player, loc.getChunk());
		player.sendBlockChange(loc, material, (byte) 0);

	}
	
	public void visualizeBlock(Player player, Location loc, Block block){
		saveChunk(player, loc.getChunk());
		player.sendBlockChange(loc, block.getTypeId(), block.getData());
	}
	
	public void visualizeBlock(Player player, Location loc, byte blockID, byte blockData) {
		saveChunk(player, loc.getChunk());
		player.sendBlockChange(loc, blockID, blockData);
	}

	public HashSet<Chunk> getPlayerChunkSet(Player player){
		HashSet<Chunk> playerChunkSet = new HashSet<Chunk>();
		for(ChunkPlayer chunk : getChunkSet()){
			if(chunk.getPlayer().getName().equals(player.getName())){
				playerChunkSet.add(chunk.getChunk());
			}
		}
		return playerChunkSet;
	}
	
	public ChunkPlayer getChunkPlayer(Chunk chunk, Player player){
		for(ChunkPlayer cp : getChunkSet()){
			if(cp.getChunk() == chunk && cp.getPlayer() == player){
				return cp;
			}
		}
		plugin.logSevere("getChunkPlayer returned NULL");
		return null;
	}
	
	/**
	 * @param player - The player who's chunks will be reverted
	 */
	@SuppressWarnings("unchecked")
	public void revertChunks(Player player){
		EntityPlayer ep = ((CraftPlayer)player).getHandle();
		for(Chunk chunk : getPlayerChunkSet(player)){
			disableChunkPlayerItem(getChunkPlayer(chunk, player));
			ep.chunkCoordIntPairQueue.add(new ChunkCoordIntPair(chunk.getX(), chunk.getZ()));
		}
	         
	}
	/**
	 * @Description - Forces a chunk update for every player within view distance of this chunk.
	 * @Warning - This method may caused visualization errors, because reloaded chunks are not scheduled
	 * for cleanup.
	 */
	@SuppressWarnings("unchecked")
	public void revertChunk(Chunk chunk){
				
        Chunk currentChunk = chunk;
        
        int diffX, diffZ;
        int viewDistance = Bukkit.getServer().getViewDistance() << 4;
        
        net.minecraft.server.World mcWorld = ((CraftChunk) currentChunk).getHandle().world;
        
        for (EntityPlayer ep : (List<EntityPlayer>) mcWorld.players) {
            diffX = (int) Math.abs(ep.locX - (currentChunk.getX() << 4)); //distances between player loc and current chunk
            diffZ = (int) Math.abs(ep.locZ - (currentChunk.getZ() << 4));
            if (diffX <= viewDistance && diffZ <= viewDistance){
                ep.chunkCoordIntPairQueue.add(new ChunkCoordIntPair(currentChunk.getX(), currentChunk.getZ()));
            }
        }       
	}
	
	private void saveChunk(Player player, Chunk chunk){
		for(ChunkPlayer savedChunk : getChunkSet()){
			if(savedChunk.getChunk() == chunk && savedChunk.getPlayer().equals(player.getName())) return;
		}
		HashSet<ChunkPlayer> temp = new HashSet<ChunkPlayer>();
		ChunkPlayer cp = new ChunkPlayer(player, chunk);
		temp.add(cp);
		chunks = temp;
	}
	
	private HashSet<ChunkPlayer> getChunkSet(){
		return chunks;
	}
	
	/**
	 * Cleans up disabled array elements. 
	 */
	public void cleanUp(){
		HashSet<ChunkPlayer> temp = new HashSet<ChunkPlayer>();
		for(ChunkPlayer cp : getChunkSet()){
			if(cp.isDisabled() == true){
				temp.remove(cp);
			}
		}
		this.chunks = temp;
		plugin.log("Cleaned up "+temp.size()+" ChunkPlayers");
	}
	
	private void disableChunkPlayerItem(ChunkPlayer chunkPlayer){
		for(ChunkPlayer cp : getChunkSet()){
			if(chunkPlayer == cp){
				cp.disable();
			}
		}
	}
	
	public void visualizeBeacons(Player player){
		for(BeaconData beacon :plugin.beaconMaster.getBeacons()){
			BeaconVis bv = new BeaconVis(plugin, player, beacon.getLocation());
			bv.visualize();
		}
	}
	
}
