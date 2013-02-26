package us.twoguys.thedarkness.player;

import java.util.HashSet;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

/**
 * 
 * @author Nick
 * 
 */
public class PlayerMaster {

	TheDarkness plugin;
	
	HashSet<DarkPlayer> darkPlayers = new HashSet<DarkPlayer>();
	
	public PlayerMaster(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void addPlayer(DarkPlayer player){
		for(DarkPlayer p : darkPlayers){
			if(player.name.equals(p.name)){
				return;
			}
		}
		this.darkPlayers.add(player);
	}
	
	public HashSet<DarkPlayer> getDarkPlayers(){
		return darkPlayers;
	}
	
	public DarkPlayer getDarkPlayer(String name){
		for (DarkPlayer p : darkPlayers){
			if(p.name==name){
				return p;
			}
		}
		DarkPlayer newDP = new DarkPlayer();
		newDP.setName(name);
		return newDP;
	}
	
	public DarkPlayer getDarkPlayer(Player p){
		for(DarkPlayer dp : darkPlayers){
			if(p.getName().equals(dp.getName())){
				return dp;
			}
		}
		DarkPlayer newDP = new DarkPlayer();
		newDP.setName(p.getName());
		return newDP;
	}
	
	
}
