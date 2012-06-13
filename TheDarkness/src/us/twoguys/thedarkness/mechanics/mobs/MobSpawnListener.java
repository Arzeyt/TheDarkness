package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.MetadataValue;

import us.twoguys.thedarkness.TheDarkness;

public class MobSpawnListener implements Listener{

	TheDarkness plugin;
	ArrayList<String> mobs;
	ArrayList<Integer> mobSettings;
	int mobDistance, mobLevel, chance;
	Random random = new Random();
	List<MetadataValue> mobMD;
	
	public MobSpawnListener(TheDarkness instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onMonsterSpawn(CreatureSpawnEvent event){
		Entity mobE = event.getEntity();
		String mobName = mobE.getType().getName();
		mobName = plugin.mobMaster.getMobName(mobName);
		mobMD = mobE.getMetadata("Darkness");
		
		mobDistance = plugin.beaconMaster.distanceFromNearestBeacon(mobE.getLocation());
		mobLevel = plugin.config.getLevel(mobDistance);
		mobs = plugin.config.getLevelMobTypes(mobLevel);
		
		if(mobs.contains("ALL")){
			mobSettings = plugin.config.getMobSettings("ALL", mobLevel);
			chance = mobSettings.get(0);
			if(passPercentChance(chance)){
				return;
			}else{
				event.setCancelled(true);
				return;
			}
		}
		try{
			chance = mobSettings.get(0);
		}catch(NullPointerException e){
			chance = 100;
		}
		
		mobDistance = plugin.beaconMaster.distanceFromNearestBeacon(mobE.getLocation());
		mobLevel = plugin.config.getLevel(mobDistance);
		mobs = plugin.config.getLevelMobTypes(mobLevel);
		
		//debug
		plugin.debug("mob: "+mobName+"   mob level: "+mobLevel);
		plugin.debug("Possible mobs: ");
		for(String mob: mobs){
			plugin.debug(mob+"");
		}

		if(mobE.hasMetadata("Darkness")){
			plugin.debug("Metadata found-"+mobName+" Allowed to spawn");
			return;
		}
		if(mobs.contains(mobName)){
			mobSettings = plugin.config.getMobSettings(mobName, mobLevel);
			if(passPercentChance(chance)){
				plugin.debug(mobName+" was allowed to spawn");
			}else{
				event.setCancelled(true);
				plugin.debug(mobName+" was spawn cancelled.");
			}
		}else{
			plugin.debug(mobName+" did not exist in mobs");
		}
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
}
