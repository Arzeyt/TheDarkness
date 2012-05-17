package us.twoguys.thedarkness;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;

public class Config {

	TheDarkness plugin;
	
	ArrayList<Integer> levelDistances = new ArrayList<Integer>();
	ArrayList<HashMap<Class<?>, ArrayList<Integer>>> levelEffects = new ArrayList<HashMap<Class<?>, ArrayList<Integer>>>();
	ArrayList<String> levelMessages = new ArrayList<String>();
	HashMap<Integer, Integer> itemPointValues = new HashMap<Integer, Integer>();
	
	public Config(TheDarkness instance){
		plugin = instance;
	}
	
	public void loadConfiguration(){
		//beacon
		String[] itemPointValues = {"BONE: 1", "ROTTEN_FLESH: 1", "SPIDER_EYE: 5", "ENDER_PEARL: 25"};
		
		plugin.getConfig().addDefault("Beacon.Cost", 100);
		plugin.getConfig().addDefault("Beacon.ItemPointValues", itemPointValues);
		
		//darkness
		plugin.getConfig().addDefault("Darkness.PlayerCheckFrequency", 20);
		
		//level1
		String[] effects1 = {"Message:Yousenseanevilpresence", "TorchConsume:200"};
		String[] mobSpawns1 = {"ZOMBIE:10"};
		String[] mirages1 = {"House:2", "Diamond:20"};
		
		plugin.getConfig().addDefault("Darkness.Level_1.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Level_1.Distance", 25);
		plugin.getConfig().addDefault("Darkness.Level_1.Effects", effects1);
		plugin.getConfig().addDefault("Darkness.Level_1.MobSpawns", mobSpawns1);
		plugin.getConfig().addDefault("Darkness.Level_1.Mirages", mirages1);
		
		//level2
		String[] effects2 = {"Message: The Darkness grows stronger around you", "LifeDrain: 1 80", "TorchConsume: 100"};
		String[] mobSpawns2 = {"ZOMBIE: 100", "SPIDER: 10"};
		String[] mirages2 = {"House: 10", "Diamond: 50"};
		
		plugin.getConfig().addDefault("Darkness.Level_2.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Level_2.Distance", 50);
		plugin.getConfig().addDefault("Darkness.Level_2.Effects", effects2);
		plugin.getConfig().addDefault("Darkness.Level_2.MobSpawns", mobSpawns2);
		plugin.getConfig().addDefault("Darkness.Level_2.Mirages", mirages2);
		
		//mirage attributes
		String[] houseMobTypes = {"ZOMBIE: 3"};
		
		plugin.getConfig().addDefault("Darkness.MirageAttributes.House.MobTypes", houseMobTypes);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		//setLevels();
		//setItemPointValues();
	}
	
	public void configError(String path){
		plugin.logSevere("Error reading config file at '" + path + "'. Make sure this section of the config is formatted correctly. For help, visit TheDarkness' bukkit dev page.");
	}
	
	public void setLevels(){
		int counter = 1;
		
		//Loop through levels
		while (plugin.getConfig().contains("Darkness.Levels." + counter)){
			
			//Set Distance Array
			levelDistances.add(plugin.getConfig().getInt("Darkness.Levels." + counter));
			
			//Set Effects and Messages Arrays
			HashMap<Class<?>, ArrayList<Integer>> effects = new HashMap<Class<?>, ArrayList<Integer>>();
			ArrayList<Integer> ints = new ArrayList<Integer>();
			boolean messageSet = false;
			int pCounter = 1;
			
			for (String s: plugin.getConfig().getStringList("Darkness.Levels." + counter + ".Effects")){
				String[] split = s.split(":");
				String[] splitAll = s.split(" ");
				
				//Set Messages Array
				if (split[0].equalsIgnoreCase("Messages") && messageSet == false){
					levelMessages.add(split[1]);
					messageSet = true;
				//Set Effects Array
				}else{
					try {
						Class<?> c = Class.forName("us.twoguys.thedarkness.mechanics.effects." + s);
						
						while (pCounter <= splitAll.length){
							ints.add(Integer.parseInt(splitAll[pCounter]));
						}
						
						effects.put(c, ints);
					} catch (ClassNotFoundException e) {
						configError("Darkness.Levels." + counter + ".Effects." + split[0]);
					}
				}
			}
			
			//If there was no message for the level
			if (messageSet == false){
				levelMessages.add("");
			}
			
			levelEffects.add(effects);
			
			//Set Mirages Array
			
			//Set Mob Spawn Array
			
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
	
	public ArrayList<Class<?>> getLevelEffectClasses(int level){
		return new ArrayList<Class<?>>(levelEffects.get(level).keySet());
	}
	
	public String getLevelMessage(int level){
		return levelMessages.get(level);
	}
	
	public ArrayList<Integer> getEffectsSettings(Class<?> effect, int level){
		return levelEffects.get(level).get(effect);
	}
	
}
