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

public class LocationCheckScheduler {

	TheDarkness plugin;
	
	int taskId;
	private HashMap<String, Integer> tasks = new HashMap<String, Integer>();
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
						//plugin.debug("There are no beacons");
						break;
					}
					
					//plugin.debug("Checking " + player.getName() + ": Level " + level);
					
					if (hasChangedLevels(player, level)){
						
						//Effects
						ArrayList<Class<?>> effects = plugin.config.getLevelEffectClasses(level);
						
						if (!effects.isEmpty()){
							for (Class<?> c: effects){
								
								Constructor<?> cons = null;
								
								if(c.getSimpleName().equalsIgnoreCase("Time")){
									plugin.debug("Passed Time");
								}else{
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
						}
						
						ArrayList<Class<?>> mirages = plugin.config.getLevelMirageClasses(level);
						
						//Mirages
						if (!mirages.isEmpty()){
							for (Class<?> c: mirages){
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
						}else{
							plugin.debug("Mirages was empty");
						}
						
						//Custom Mirages
						ArrayList<String> customMirages = plugin.config.getLevelCustomMirages(level);
						
						if(!customMirages.isEmpty()){
							for(String mirage : customMirages){
								try{
									CustomMirage c = new CustomMirage(plugin, player, level, mirage);
									plugin.debug("Activated "+mirage);
								}catch(Exception e){
									plugin.debug("Could not instantiate "+mirage);
									e.printStackTrace();
								}
							}
						}else{
							plugin.debug("CustomMirages was empty");
						}
						
						//Mobs
						
						ArrayList<String> mobTypes = plugin.config.getLevelMobTypes(level);
						
						if(!mobTypes.isEmpty()){
							for(String mob : mobTypes){
								try{
									Mob m = new Mob(plugin, player, level, mob);
								}catch(Exception e){
									plugin.debug("Could not instantiate "+mob+" spawner");
									e.printStackTrace();
								}
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
			
			PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(player, level, level);
			Bukkit.getServer().getPluginManager().callEvent(event);
			return true;
		}else{
			if(playerLevels.get(player) == level){
				return false;
			}else if(player.isOnline()==false){
				playerLevels.remove(player);
				return true;
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
