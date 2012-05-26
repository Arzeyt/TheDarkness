package us.twoguys.thedarkness.mechanics;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.mechanics.effects.Potion;

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
						
						if (!effects.isEmpty()){
							for (Class<?> c: effects){
								
								Constructor<?> cons = null;
								
								try{
									cons = c.getConstructor(TheDarkness.class, Player.class, int.class);
								}catch(Exception e){
									plugin.debug("Failed to get constructor: " + c.getSimpleName());
									e.printStackTrace();
								}
								
								try{
									cons.newInstance(plugin, player, level);
								}catch(Exception e){
									plugin.debug("Failed to use constructor: " + c.getSimpleName());
									e.printStackTrace();
								}
							}
						}
						
						//Potions
						ArrayList<ArrayList<Integer>> potions = plugin.config.getLevelPotionEffects(level);
						
						if (!potions.isEmpty()){
							for (ArrayList<Integer> settings: potions){
								new Potion(plugin, player, level, settings);
								plugin.debug("Adding Potion: " + PotionEffectType.getById(settings.get(0)).getName());
							}
						}else{
							plugin.debug("Level potions was empty");
							continue;
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
	
	public int getDarknessLevel(Player player){
		return playerLevels.get(player);
	}
}
