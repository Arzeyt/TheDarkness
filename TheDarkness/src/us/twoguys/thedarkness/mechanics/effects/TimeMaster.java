package us.twoguys.thedarkness.mechanics.effects;

import java.util.HashMap;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class TimeMaster {

	HashMap<Player, Integer> playerClock = new HashMap<Player, Integer>();
	
	TheDarkness plugin;
	
	public TimeMaster(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void setTime(Player player, int time){
		if(playerClock.containsKey(player)==false){
			playerClock.put(player, 0);
		}
		playerClock.put(player, time);
	}
	
	public Integer getTime(Player player){
		if(playerClock.containsKey(player)==false){
			playerClock.put(player, 0);
		}
		return playerClock.get(player);
	}
	

}
