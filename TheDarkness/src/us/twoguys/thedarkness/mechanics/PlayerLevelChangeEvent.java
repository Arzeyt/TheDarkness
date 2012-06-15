package us.twoguys.thedarkness.mechanics;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLevelChangeEvent extends Event{

	private Player player;
	private int from, to;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerLevelChangeEvent(Player player, int from, int to){
		super();
		this.player = player;
		this.from = from;
		this.to = to;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getLevelFrom(){
		return from;
	}
	
	public int getLevelTo(){
		return to;
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
