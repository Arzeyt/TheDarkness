package us.twoguys.thedarkness;

import java.util.Arrays;

public class Config {

	TheDarkness plugin;
	
	public Config(TheDarkness instance){
		plugin = instance;
	}
	
	public void loadConfiguration(){
		
		//beacon
		plugin.getConfig().addDefault("Beacon.Cost", 100);
		String[] itemPointValues = {"BONE: 1", "ROTTEN_FLESH: 1", "SPIDER_EYE: 5", "ENDER_PEARL: 25"};
		plugin.getConfig().addDefault("Beacon.ItemPointValues", Arrays.asList(itemPointValues));
		
		//darkness
		plugin.getConfig().addDefault("Darkness.CheckFrequency", 20);
		
		//level1
		plugin.getConfig().addDefault("Darkness.Levels.1.Distance", 25);
		String[] effects1 = {"message: You sense an evil presence", "torchconsume: 200"};
		plugin.getConfig().addDefault("Darkness.Levels.1.Effects", Arrays.asList(effects1));
		String[] mobSpawns1 = {"ZOMBIE: 10 20"};
		plugin.getConfig().addDefault("Darkness.Levels.1.MobSpawns", Arrays.asList(mobSpawns1));
		String[] mirages1 = {"house: 2 20", "diamond: 20 20"};
		plugin.getConfig().addDefault("Darkness.Levels.1.Mirages", Arrays.asList(mirages1));
		
		//level2
		plugin.getConfig().addDefault("Darkness.Levels.2.Distance", 50);
		String[] effects2 = {"message: The Darkness grows stronger around you", "lifedrain: 1 80", "torchconsume: 100"};
		plugin.getConfig().addDefault("Darkness.Levels.2.Effects", Arrays.asList(effects2));
		String[] mobSpawns2 = {"ZOMBIE: 100 20", "SPIDER: 10 20"};
		plugin.getConfig().addDefault("Darkness.Levels.2.MobSpawns", Arrays.asList(mobSpawns2));
		String[] mirages2 = {"house: 10 20", "diamond: 50 20"};
		plugin.getConfig().addDefault("Darkness.Levels.2.Mirages", Arrays.asList(mirages2));
		
		//mirage attributes
		String[] houseMobTypes = {"ZOMBIE: 3"};
		plugin.getConfig().addDefault("Darkness.MirageAttributes.house.mobtypes", Arrays.asList(houseMobTypes));
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
