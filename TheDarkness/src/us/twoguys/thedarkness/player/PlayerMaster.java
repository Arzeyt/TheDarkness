package us.twoguys.thedarkness.player;

import java.util.HashSet;

import us.twoguys.thedarkness.TheDarkness;

/**
 * 
 * @author Nick
 * 
 */
public class PlayerMaster {

	TheDarkness plugin;
	
	HashSet<DarkPlayer> players = new HashSet<DarkPlayer>();
	
	public PlayerMaster(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void addPlayer(DarkPlayer player){
		for(DarkPlayer p : players){
			if(player.name.equals(p.name)){
				return;
			}
		}
		this.players.add(player);
	}
	
	public HashSet<DarkPlayer> getDarkPlayers(){
		return players;
	}
	
	public DarkPlayer getDarkPlayer(String name){
		for (DarkPlayer p : players){
			if(p.name==name){
				return p;
			}
		}
		DarkPlayer player = new DarkPlayer();
		player.setName(name);
		return player;
	}
}
