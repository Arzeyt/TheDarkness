package us.twoguys.thedarkness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;

public class Config {

	TheDarkness plugin;
	
	ArrayList<Integer> levelDistances = new ArrayList<Integer>();
	HashMap<Integer, Integer> itemPointValues = new HashMap<Integer, Integer>();
	
	public Config(TheDarkness instance){
		plugin = instance;
	}
	
	public void loadConfiguration(){
		
		//beacon
		String[] itemPointValues = {"BONE: 1", "ROTTEN_FLESH: 1", "SPIDER_EYE: 5", "ENDER_PEARL: 25"};
		
		plugin.getConfig().addDefault("Beacon.Cost", 100);
		plugin.getConfig().addDefault("Beacon.ItemPointValues", Arrays.asList(itemPointValues));
		
		//darkness
		plugin.getConfig().addDefault("Darkness.PlayerCheckFrequency", 20);
		
		//level1
		String[] effects1 = {"Message: You sense an evil presence", "TorchConsume: 200"};
		String[] mobSpawns1 = {"ZOMBIE: 10"};
		String[] mirages1 = {"House: 2", "Diamond: 20"};
		
		plugin.getConfig().addDefault("Darkness.Levels.1.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Levels.1.Distance", 25);
		plugin.getConfig().addDefault("Darkness.Levels.1.Effects", Arrays.asList(effects1));
		plugin.getConfig().addDefault("Darkness.Levels.1.MobSpawns", Arrays.asList(mobSpawns1));
		plugin.getConfig().addDefault("Darkness.Levels.1.Mirages", Arrays.asList(mirages1));
		
		//level2
		String[] effects2 = {"Message: The Darkness grows stronger around you", "LifeDrain: 1 80", "TorchConsume: 100"};
		String[] mobSpawns2 = {"ZOMBIE: 100", "SPIDER: 10"};
		String[] mirages2 = {"House: 10", "Diamond: 50"};
		
		plugin.getConfig().addDefault("Darkness.Levels.2.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Levels.2.Distance", 50);
		plugin.getConfig().addDefault("Darkness.Levels.2.Effects", Arrays.asList(effects2));
		plugin.getConfig().addDefault("Darkness.Levels.2.MobSpawns", Arrays.asList(mobSpawns2));
		plugin.getConfig().addDefault("Darkness.Levels.2.Mirages", Arrays.asList(mirages2));
		
		//mirage attributes
		String[] houseMobTypes = {"ZOMBIE: 3"};
		
		plugin.getConfig().addDefault("Darkness.MirageAttributes.House.MobTypes", Arrays.asList(houseMobTypes));
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		setLevelDistances();
		setItemPointValues();
	}
	
	public void configError(String path){
		plugin.logSevere("Error reading config file at '" + path + "'. Make sure this section of the config is formatted correctly. For help, visit TheDarkness' bukkit dev page.");
	}
	
	public void setLevelDistances(){
		int counter = 1;
		
		while (plugin.getConfig().contains("Darkness.Levels." + counter)){
			levelDistances.add(plugin.getConfig().getInt("Darkness.Levels." + counter));
			counter++;
		}
	}
	
	public void setItemPointValues(){
		for (String s: plugin.getConfig().getStringList("Beacon.ItemPointValues")){
			String[] split = s.split(": ");
			
			try{
				itemPointValues.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			}catch(Exception e){
				try{
					itemPointValues.put(Material.valueOf(split[0]).getId(), Integer.parseInt(split[1]));
				}catch(Exception e2){
					configError("Beacon.ItemPointValues." + s);
				}
			}
		}
	}
	
	public int getLevel(int dist){
		for (int levDist: levelDistances){
			if (dist < levDist){
				return levelDistances.indexOf(levDist);
			}
		}
		return 0;
	}
	
	public boolean isWorthBeaconPoints(Material mat){
		return (itemPointValues.containsKey(mat.getId()) ? true : false);
	}
	
	public int getItemBeaconPointValue(Material mat){
		return (itemPointValues.containsKey(mat.getId()) ? itemPointValues.get(mat.getId()) : 0);
	}
	
	public int getPlayerCheckFreq(){
		return plugin.getConfig().getInt("Darkness.PlayerCheckFrequency");
	}
	
	public ArrayList<Class<?>> getEffectClasses(int level){
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (String s: plugin.getConfig().getStringList("Darkness.Levels." + level + ".Effects")){
			String[] split = s.split(":");
			//TODO add class locations for each effect.
			
		}
		
		return classes;
	}
}
