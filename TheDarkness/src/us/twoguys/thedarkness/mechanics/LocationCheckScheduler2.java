package us.twoguys.thedarkness.mechanics;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.mechanics.effects.Potion;
import us.twoguys.thedarkness.mechanics.mirages.CustomMirage;
import us.twoguys.thedarkness.mechanics.mobs.Mob;
import us.twoguys.thedarkness.player.DarkPlayer;
import us.twoguys.thedarkness.player.DarkPlayerDoesNotExistException;

public class LocationCheckScheduler2 {

	TheDarkness plugin;
	
	int taskId;
	private HashMap<String, Integer> tasks = new HashMap<String, Integer>();
	HashMap<Player, Integer> playerLevels = new HashMap<Player, Integer>();
	
	public LocationCheckScheduler2(TheDarkness instance){
		plugin = instance;
	}
	
	public void checkPlayerLocations(){
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			public void run(){
				

				for (Player player: Bukkit.getOnlinePlayers()){
					DarkPlayer dp;
					dp = plugin.playerMaster.getDarkPlayer(player.getName());
					
					int level = plugin.beaconMaster.getDarknessLevel(player);
					dp.setLevel(level);
					
					if(level==-1){
						//plugin.debug("There are no beacons");
						break;
					}
					
					//plugin.debug("Checking " + player.getName() + ": Level " + level);
					
					if (hasChangedLevels(player, level)){
						
						
					}
					
				}
			}
		}, 0L, plugin.config.getPlayerCheckFreq());
	}
	
	public boolean hasChangedLevels(Player player, int level){
		if (!playerLevels.containsKey(player)){
			playerLevels.put(player, level);
			
			PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(player, level, level);
			Bukkit.getServer().getPluginManager().callEvent(event);
			return true;
		}else{
			if(playerLevels.get(player) == level){
				return false;
			}else if(player.isOnline()==false){
				playerLevels.remove(player);
				return false;
			}else{
				PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(player, playerLevels.get(player), level);
				Bukkit.getServer().getPluginManager().callEvent(event);
				
				playerLevels.remove(player);
				playerLevels.put(player, level);
				return true;
			}
		}
	}
	
	public int getDarknessLevel(Player player){
		return playerLevels.get(player);
	}
	
	public void addTask(String name, int taskID){
		if(tasks.containsKey(name)){
			Bukkit.getServer().getScheduler().cancelTask(tasks.get(name));
		}
		tasks.put(name, taskID);
	}
	
	public void cancelTask(String name){
		try{
			Bukkit.getServer().getScheduler().cancelTask(tasks.get(name));
		}catch(Exception e){
			plugin.debug("no task to cancel");
		}
	}
	
}
