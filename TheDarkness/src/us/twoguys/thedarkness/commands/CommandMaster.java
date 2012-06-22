package us.twoguys.thedarkness.commands;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class CommandMaster {

	public HashMap<Player, String> playerSelection = new HashMap<Player, String>();
	
	public HashMap<Player, String> getPlayerSelection(){
		return playerSelection;
	}
	
	public String getString(Player player){
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
