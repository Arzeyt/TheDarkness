package us.twoguys.thedarkness;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

public class Config {

	TheDarkness plugin;
	
	ArrayList<Integer> levelDistances = new ArrayList<Integer>();
	HashMap<Integer, ArrayList<String>> levelMessages = new HashMap<Integer, ArrayList<String>>();
	ArrayList<Integer> levelTorchConsume = new ArrayList<Integer>();
	ArrayList<Integer> levelEffectFreq = new ArrayList<Integer>();
	HashMap<Integer, Integer> itemPointValues = new HashMap<Integer, Integer>();
	
	ArrayList<HashMap<String, ArrayList<Integer>>> levelStrings = new ArrayList<HashMap<String, ArrayList<Integer>>>();
	ArrayList<HashMap<Class<?>, ArrayList<Integer>>> levelEffects = new ArrayList<HashMap<Class<?>, ArrayList<Integer>>>();
	ArrayList<ArrayList<ArrayList<Integer>>> levelPotions = new ArrayList<ArrayList<ArrayList<Integer>>>();
	ArrayList<HashMap<String, ArrayList<Integer>>> levelMobs = new ArrayList<HashMap<String, ArrayList<Integer>>>();
	ArrayList<HashMap<Class<?>, ArrayList<Integer>>> levelMirages = new ArrayList<HashMap<Class<?>, ArrayList<Integer>>>();
	ArrayList<HashMap<String, ArrayList<Integer>>> levelCustomMirages = new ArrayList<HashMap<String, ArrayList<Integer>>>();
	
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
		
		//level0
		String[] effects0 = {"Message: You feel the warmth of the light."};
		String[] mobSpawns0 = {};
		String[] mirages0 = {};
		
		plugin.getConfig().addDefault("Darkness.Levels.0.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Levels.0.Effects", effects0);
		
		//level1
		String[] effects1 = {"Message: You sense an evil presence: The evil presence weakens", "TorchConsume: 200"};
		String[] mobSpawns1 = {"Zombie: 1 25 7 1 200"};
		String[] mirages1 = {""};
		
		plugin.getConfig().addDefault("Darkness.Levels.1.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Levels.1.Distance", 100);
		plugin.getConfig().addDefault("Darkness.Levels.1.Effects", effects1);
		plugin.getConfig().addDefault("Darkness.Levels.1.MobSpawns", mobSpawns1);
		plugin.getConfig().addDefault("Darkness.Levels.1.Mirages", mirages1);
		
		//level2
		String[] effects2 = {"Message: The Darkness grows stronger around you", "TorchConsume: 100",
				"Weakness: 25 10 1"};
		String[] mobSpawns2 = {"Zombie: 1 50 7 1 200"};
		String[] mirages2 = {""};
		
		plugin.getConfig().addDefault("Darkness.Levels.2.DefaultEffectCheckFrequency", 20);
		plugin.getConfig().addDefault("Darkness.Levels.2.Distance", 150);
		plugin.getConfig().addDefault("Darkness.Levels.2.Effects", effects2);
		plugin.getConfig().addDefault("Darkness.Levels.2.MobSpawns", mobSpawns2);
		plugin.getConfig().addDefault("Darkness.Levels.2.Mirages", mirages2);
		
		//mirage attributes
		String[] houseMobTypes = {""};
		
		plugin.getConfig().addDefault("Darkness.MirageAttributes.House.MobTypes", houseMobTypes);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		//Set Config Resources
		setItemPointValues();
		setLevels();
		
	}
	
	public int getLevel(int distance){
		if(distance == 123456789){
			plugin.debug("distance is default");
			return -1;
		}
		
		if (distance < levelDistances.get(1)){
			//plugin.debug("Distance is less than lvl 1");
			return 0;
		}
		
		for (int levDist: levelDistances){
			
			try{
				if (distance > levDist && distance <= levelDistances.get(levelDistances.indexOf(levDist) + 1)){
					return levelDistances.indexOf(levDist);
				}
			}catch(Exception e){
				if (distance > levDist){
					return levelDistances.indexOf(levDist);
				}
			}
		}
		return 0;
	}
	
	public int getBeaconCost(){
		return plugin.getConfig().getInt("Beacon.Cost");
	}
	public boolean isWorthBeaconPoints(Material mat){
		return (itemPointValues.containsKey(mat.getId()) ? true : false);
	}
	
	public int getItemBeaconPointValue(Material mat){
		
		for (int i: itemPointValues.keySet()){
			plugin.debug(i + ": " + itemPointValues.get(i));
		}
		
		return (itemPointValues.containsKey(mat.getId()) ? itemPointValues.get(mat.getId()) : 0);
	}
	
	public int getPlayerCheckFreq(){
		return plugin.getConfig().getInt("Darkness.PlayerCheckFrequency");
	}
	
	public int getDefaultEffectCheckFreq(int level){
		return levelEffectFreq.get(level);
	}
	
	public ArrayList<String> getLevelMessage(int level){
		ArrayList<String> messages = new ArrayList<String>();
		
		if(level < 0){
			messages.add("There are no beacons in this world...");
		}
		return levelMessages.get(level);
	}
	
	public int getLevelTorchConsumeTime(int level){
		return levelTorchConsume.get(level);
	}
	
	public ArrayList<Class<?>> getLevelEffectClasses(int level){
		return new ArrayList<Class<?>>(levelEffects.get(level).keySet());
	}
	
	public ArrayList<Integer> getEffectsSettings(Class<?> effect, int level){
		return levelEffects.get(level).get(effect);
	}
	
	public ArrayList<ArrayList<Integer>> getLevelPotionEffects(int level){
		return levelPotions.get(level);
	}
	
	public ArrayList<String> getLevelMobTypes(int level){
		return new ArrayList<String>(levelMobs.get(level).keySet());
	}
	
	public ArrayList<Integer> getMobSettings(String mob, int level){
		return levelMobs.get(level).get(mob);
	}
	
	public ArrayList<Class<?>> getLevelMirageClasses(int level){
		return new ArrayList<Class<?>>(levelMirages.get(level).keySet());
	}
	
	public ArrayList<Integer> getMirageSettings(Class<?> mirage, int level){
		return levelMirages.get(level).get(mirage);
	}
	
	public ArrayList<String> getLevelCustomMirages(int level){
		return new ArrayList<String>(levelCustomMirages.get(level).keySet());
		
	}
	
	public ArrayList<Integer> getCustomMirageSettings(String mirage, int level){
		return new ArrayList<Integer>(levelCustomMirages.get(level).get(mirage));
	}
	
	public void setLevels(){
		
		plugin.reloadConfig();
		
		plugin.debug("Setting level arrays");
		
		int counter = 0;
		
		//Loop through levels
		while (plugin.getConfig().contains("Darkness.Levels." + counter)){
			
			plugin.debug("Level " + counter + "_____________________________________");
			
			//Set Distance Array
			if (counter != 0){
				levelDistances.add(plugin.getConfig().getInt("Darkness.Levels." + counter + ".Distance"));
				plugin.debug("Setting Distance: " + plugin.getConfig().getInt("Darkness.Levels." + counter + ".Distance"));
			}else{
				levelDistances.add(0);
			}
			
			//Set Default Effect Check Frequency Array
			levelEffectFreq.add(plugin.getConfig().getInt("Darkness.Levels." + counter + ".DefaultEffectCheckFrequency"));
			plugin.debug("Setting Default Effect Check Freq: " + plugin.getConfig().getInt("Darkness.Levels." + counter + ".DefaultEffectCheckFrequency"));
			
			//Set Effects and Messages Arrays
			HashMap<Class<?>, ArrayList<Integer>> effects = new HashMap<Class<?>, ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> potions = new ArrayList<ArrayList<Integer>>();
			boolean messageSet = false;
			boolean tConsumeSet = false;
			
			plugin.debug("Checking Effects");
			
			for (String s: plugin.getConfig().getStringList("Darkness.Levels." + counter + ".Effects")){
				ArrayList<Integer> ints = new ArrayList<Integer>();
				
				String[] split = s.split(": ");
				String[] splitAll = s.split(" ");
				
				int pCounter = 1;
				
				plugin.debug("Effect Name: " + split[0]);

				//Attempt to add potion Effect
				try{
					PotionEffectType pot = PotionEffectType.getByName(split[0].toUpperCase());
					ints.add(pot.getId());
					
					plugin.debug("Found Potion: " + split[0].toUpperCase());
					
					while (pCounter < splitAll.length){
						ints.add(Integer.parseInt(splitAll[pCounter]));
						plugin.debug("Adding to Int array: " + splitAll[pCounter]);
						pCounter++;
					}
					
					potions.add(ints);
					
				}catch(Exception e){
					//Set Messages Array
					if (split[0].equalsIgnoreCase("Message") && messageSet == false){
						ArrayList<String> messages = new ArrayList<String>();
						
						messages.add(split[1]);
						
						if(split.length > 2){
							messages.add(split[2]);
						}
						levelMessages.put(counter, messages);
						messageSet = true;
						plugin.debug("Added messages");
					
					//Set Torch Consume array
					}else if (split[0].equalsIgnoreCase("TorchConsume")){
						try{
							int i = Integer.parseInt(split[1]);
							levelTorchConsume.add(i);
							tConsumeSet = true;
							
							plugin.debug("Added Torch Consume time: " + i + " seconds");
						}catch(Exception e1){
							levelTorchConsume.add(123456789);
							tConsumeSet = true;
							configError("Darkness.Levels." + counter + "Effects.TorchConsume");
						}
						
					//Set Effects Array
					}else{
						
						try {
							Class<?> c = Class.forName("us.twoguys.thedarkness.mechanics.effects." + split[0]);
							
							plugin.debug("Found Class: us.twoguys.thedarkness.mechanics.effects." + split[0]);
							
							while (pCounter < splitAll.length){
								ints.add(Integer.parseInt(splitAll[pCounter]));
								plugin.debug("Adding to Int array: " + splitAll[pCounter]);
								pCounter++;
							}
							
							effects.put(c, ints);
							plugin.debug("Successully added Effect class and settings to hashMap");
						} catch (ClassNotFoundException e2) {
							configError("Darkness.Levels." + counter + ".Effects." + split[0]);
							continue;
						}
					}
				}
			}
			
			//If there was no message for the level
			if (messageSet == false){
				HashMap<Integer, ArrayList<String>> temp = new HashMap<Integer, ArrayList<String>>();
				ArrayList<String> t = new ArrayList<String>();
				t.add("");
				t.add("");
				temp.put(counter, t);
			}
			
			//If no Torch consume for the level
			if (tConsumeSet == false){
				levelTorchConsume.add(123456789);
			}
			
			levelPotions.add(potions);
			plugin.debug("Added all Potions for this level");
			
			levelEffects.add(effects);
			plugin.debug("Added all effects for this level");
			
			//Set Mob Spawn Array
			HashMap<String, ArrayList<Integer>> mobSettings = new HashMap<String, ArrayList<Integer>>();
			
			plugin.debug("Checking Mob Spawns");
			
			for (String s: plugin.getConfig().getStringList("Darkness.Levels." + counter + ".MobSpawns")){
				ArrayList<Integer> ints = new ArrayList<Integer>();
				String[] split = s.split(":");
				String[] splitAll = s.split(" ");
				String mob = null;
				
				mob = plugin.mobMaster.getMobName(split[0]);
				
				int pCounter = 1;
				
				while (pCounter < splitAll.length){
					ints.add(Integer.parseInt(splitAll[pCounter]));
					plugin.debug("Adding to Int array: " + splitAll[pCounter]);
					pCounter++;
				}
				
				mobSettings.put(mob, ints);
				plugin.debug("Added mob and ints to HashMap");
			}
			
			levelMobs.add(mobSettings);
			plugin.debug("Added all Mob Settings for this level");
			
			//Set Mirages Array
			HashMap<Class<?>, ArrayList<Integer>> mirages = new HashMap<Class<?>, ArrayList<Integer>>();
			
			plugin.debug("Checking Mirages");
			
			for (String s: plugin.getConfig().getStringList("Darkness.Levels." + counter + ".Mirages")){
				ArrayList<Integer> ints = new ArrayList<Integer>();
				String[] split = s.split(":");
				String[] splitAll = s.split(" ");
				
				int pCounter = 1;
				
				plugin.debug("Mirage Name: " + split[0]);
				
				try {
					Class<?> c = Class.forName("us.twoguys.thedarkness.mechanics.mirages." + split[0]);
					
					plugin.debug("Found Class: us.twoguys.thedarkness.mechanics.mirages." + split[0]);
					
					while (pCounter < splitAll.length){
						ints.add(Integer.parseInt(splitAll[pCounter]));
						plugin.debug("Adding to Int array: " + splitAll[pCounter]);
						pCounter++;
					}
					
					mirages.put(c, ints);
					plugin.debug("Added mirage and ints to HashMap");
				} catch (ClassNotFoundException e) {
					configError("Darkness.Levels." + counter + ".Mirages." + split[0]);
					continue;
				}
			}
			
			levelMirages.add(mirages);
			plugin.debug("Added all Mirage settings for this level");
			
			//Set CustomMirages 
			plugin.debug("Checking Custom Mirages");
			
			HashMap<String, ArrayList<Integer>> customMirages = new HashMap<String, ArrayList<Integer>>();
			
			for(String s : plugin.getConfig().getStringList("Darkness.Levels."+counter+".CustomMirages")){
				ArrayList<Integer> mirageSettings = new ArrayList<Integer>();
				String[] name = s.split(": ");
				String[] params = name[1].split(" ");
				
				plugin.debug("checking "+name[0]);
				for(String setting : params){
					plugin.debug("adding to Int array "+setting);
					mirageSettings.add(Integer.parseInt(setting));
				}
				
				if(plugin.schematicHandler.schematicExists(name[0])){
					plugin.debug("Loaded parameters for "+name[0]);
					customMirages.put(name[0], mirageSettings);
				}else{
					plugin.logSevere("schematic "+name[0]+" does not exist!");
				}
			}
			levelCustomMirages.add(customMirages);
			plugin.debug("Added all Custom Mirages for this level");
			
			counter++;
			plugin.debug("Level Counter++");
		}
		
		plugin.debug("Finished");
		
	}
	
	public void setItemPointValues(){
		
		plugin.reloadConfig();
		
		plugin.debug("Setting beacon item point values");
		
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
	
	public void configError(String path){
		plugin.logSevere("Error reading config file at '" + path + "'. Make sure this section of the config is formatted correctly. For help, visit TheDarkness' bukkit dev page.");
	}
	
}
