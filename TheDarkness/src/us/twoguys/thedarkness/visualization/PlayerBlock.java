package us.twoguys.thedarkness.visualization;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerBlock {

	private Player player;
	private Block block;
	private boolean isDead;
	
	public PlayerBlock(Player player, Block block){
		this.player = player;
		this.block = block;

	}
	
	public Block getBlock(){
		return block;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public void kill(){
		isDead = true;
	}
}
