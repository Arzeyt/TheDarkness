package us.twoguys.thedarkness.visualization;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ChunkPlayer {

	private Chunk chunk;
    private Player player;
	private boolean isDisabled;
	
	public ChunkPlayer(Player player, Chunk chunk){
		this.chunk = chunk;
		this.player = player;
	}
	
	public Chunk getChunk(){ return chunk;}
	
	public Player getPlayer(){return player;}
	
	public boolean isDisabled(){return isDisabled;}
	
	public void disable(boolean b){isDisabled = b;}
}
