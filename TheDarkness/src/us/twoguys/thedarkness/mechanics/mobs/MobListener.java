package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.mechanics.PlayerLevelChangeEvent;

public class MobListener implements Listener{

	TheDarkness plugin;
	ArrayList<String> mobs;
	ArrayList<Integer> mobSettings;
	int mobDistance, mobLevel;
	Random random = new Random();
	
	public MobListener(TheDarkness instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onMonsterSpawn(CreatureSpawnEvent event){
		Entity mobE = event.getEntity();
		String mobName = mobE.getType().getName();
		
		mobDistance = plugin.beaconMaster.distanceFromNearestBeacon(mobE.getLocation());
		mobLevel = plugin.config.getLevel(mobDistance);
		mobs = plugin.config.getLevelMobTypes(mobLevel);

		 
		if(mobs.contains(mobName)){
			mobSettings = plugin.config.getMobSettings(mobName, mobLevel);
			if(passPercentChance(mobSettings.get(1))){
				plugin.debug(mobName+" was spawn cancelled.");
			}else{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent event){
		plugin.debug("level change event");
		event.getPlayer().sendMessage("changed levels");
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
}
