package us.twoguys.thedarkness.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconListenerMaster {

	private HashMap<Player, String> playerSelection = new HashMap<Player, String>();
	TheDarkness plugin;
	
	public BeaconListenerMaster(TheDarkness instance){
		this.plugin = instance;
	}
	
	public HashMap<Player, String> getPlayerSelection(){
		return playerSelection;
	}
	
	public String getPlayerString(Player player){
		try{
			if(containsPlayer(player)){
				return getPlayerSelection().get(player);
			}else{
				return getPlayerSelection().put(player, null);
			}
		}catch(Exception e){
			return "null";
		}
	}
	
	public void setString(Player player, String string){
			getPlayerSelection().put(player, string);
	}
	
	public boolean containsPlayer(Player player){
		if(getPlayerSelection().keySet().contains(player)) return true; else return false;
		
	}
	
}
