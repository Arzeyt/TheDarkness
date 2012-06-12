package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.metadata.MetadataValue;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.visualization.Smoke;

/*
 * On monster target event, get target of monster. 
 * if target == player && monster.hasMetadata("Darkness") {despawn monster;}
 * else return
 */
public class MobTargetListener implements Listener{

	TheDarkness plugin;
	ArrayList<String> mobs;
	ArrayList<Integer> mobSettings;
	int mobDistance, mobLevel, chance;
	Random random = new Random();
	List<MetadataValue> mobMD;
	String mobName;
	
	public MobTargetListener(TheDarkness instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onMonsterTarget(EntityTargetLivingEntityEvent event){
		final Entity mobE = event.getEntity();
		this.mobName = plugin.mobMaster.getMobName(mobE.getType().getName());
		mobMD = mobE.getMetadata("Darkness");
		
		mobDistance = plugin.beaconMaster.distanceFromNearestBeacon(mobE.getLocation());
		mobLevel = plugin.config.getLevel(mobDistance);
		mobs = plugin.config.getLevelMobTypes(mobLevel);
		
		if(mobs.contains("ALL")){
			mobSettings = plugin.config.getMobSettings("ALL", mobLevel);
			chance = mobSettings.get(0);
			if(passPercentChance(chance)){
				despawn(mobE);
				return;
			}
		}else{
			mobSettings = plugin.config.getMobSettings(mobName, mobLevel);
			try{
				chance = mobSettings.get(0);
			}catch(NullPointerException e){
				chance = 100;
			}
			
			mobDistance = plugin.beaconMaster.distanceFromNearestBeacon(mobE.getLocation());
			mobLevel = plugin.config.getLevel(mobDistance);
			mobs = plugin.config.getLevelMobTypes(mobLevel);
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

				public void run() {
					if(mobE.hasMetadata("Darkness")==false){
						plugin.debug(mobName+" does not have metaData. Removing mob");
						despawn(mobE);
					}else{
						plugin.debug(mobName+" has metadata!");
						if(mobs.contains(mobName)){
							if(passPercentChance(chance)){
								plugin.debug(mobName+" did not pass percent chance - "+chance);
								despawn(mobE);
							}else{
								plugin.debug(mobName+" was allowed to live! chance- "+ chance);
								return;
							}
						}else{
							plugin.debug(mobName+" is not in mobs list");
						}
					}
				}
				
				
			}, 10);
		}
		
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public void despawn(Entity entity){
		entity.remove();
		Smoke smoke = new Smoke(plugin, entity.getLocation());
		smoke.expandSpam(3, 0, 1);
	}
}
