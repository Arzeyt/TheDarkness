package us.twoguys.thedarkness.mechanics;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class LocationCheckScheduler {

	TheDarkness plugin;
	
	int taskId;
	HashMap<Player, Integer> playerLevels = new HashMap<Player, Integer>();
	
	public LocationCheckScheduler(TheDarkness instance){
		plugin = instance;
	}
	
	public void checkPlayerLocations(){
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			public void run(){
				

				for (Player player: Bukkit.getOnlinePlayers()){
					int level = plugin.beaconMaster.getDarknessLevel(player);

					if(level==-1){
						plugin.debug("There are no beacons");
						break;
					}
					
					plugin.debug("Checking " + player.getName() + ": Level " + level);
					
					if (hasChangedLevels(player, level)){
						//Message
						plugin.sendMessage(player, plugin.config.getLevelMessage(level));
						
						//Effects
						ArrayList<Class<?>> effects = plugin.config.getLevelEffectClasses(level);
						
						for (Class<?> c: effects){
							try{
								c.getConstructor(c).newInstance(plugin, player, level);
							}catch(Exception e){
								plugin.debug("Effect Class failed to instantiate: " + c.getSimpleName());
							}
						}
					}
				}
			}
		}, 0L, plugin.config.getPlayerCheckFreq());
	}
	
	public boolean hasChangedLevels(Player player, int level){
		if (!playerLevels.containsKey(player)){
			playerLevels.put(player, level);
			return true;
		}else{
			if (playerLevels.get(player) == level){
				return false;
			}else{
				playerLevels.remove(player);
				playerLevels.put(player, level);
				return true;
			}
		}
	}
}
