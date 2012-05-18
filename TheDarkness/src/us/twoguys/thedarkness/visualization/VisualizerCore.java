package us.twoguys.thedarkness.visualization;

import java.util.HashSet;
import java.util.List;

import net.minecraft.server.ChunkCoordIntPair;
import net.minecraft.server.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class VisualizerCore {

	HashSet<PlayerBlock> blocks = new HashSet<PlayerBlock>();
	HashSet<ChunkPlayer> chunks = new HashSet<ChunkPlayer>();
	
	TheDarkness plugin;
	
	public VisualizerCore(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void visualizeBlock(Player player, Location loc, Material material){
		saveChunk(player, loc.getChunk());
		player.sendBlockChange(loc, material, (byte) 0);
		
		plugin.log("Visualized block");
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
	/**
	 * 
	 * @param player - The player who's chunks will be reverted
	 */
	@SuppressWarnings("unchecked")
	public void revertChunks(Player player){
		EntityPlayer ep = ((CraftPlayer)player).getHandle();
		for(Chunk chunk : getPlayerChunkSet(player)){
			
			ep.chunkCoordIntPairQueue.add(new ChunkCoordIntPair(chunk.getX(), chunk.getZ()));
		}
	         
	}
	/**
	 * @Description - Forces a chunk update for every player within veiw distance of this chunk.
	 */
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
        
		/*EntityPlayer ep = ((CraftPlayer) player).getHandle();
		ep.chunkSendQueue.add(new ChunkCoordIntPair(x, z));
		*/
		
	}
	
	private void saveChunk(Player player, Chunk chunk){
		for(ChunkPlayer savedChunk : getChunkSet()){
			if(savedChunk.getChunk() == chunk) return;
		}
		HashSet<ChunkPlayer> temp = new HashSet<ChunkPlayer>();
		ChunkPlayer cp = new ChunkPlayer(player, chunk);
		temp.add(cp);
		chunks = temp;
	}
	
	private HashSet<ChunkPlayer> getChunkSet(){
		return chunks;
	}
	
}
