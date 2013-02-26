package us.twoguys.thedarkness.mechanics.mobs;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.metadata.MetadataValue;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.config.ConfigSetting;
import us.twoguys.thedarkness.config.DarkLevel;
import us.twoguys.thedarkness.config.SettingEnum;

public class MobSpawnListener implements Listener{

	TheDarkness plugin;
	int mobLevel, 
	chance;
	HashSet<String> mobs;
	Random random = new Random();
	List<MetadataValue> mobMD;
	DarkLevel darkLevel;
	
	ConfigSetting mobSettings; 
	
	
	public MobSpawnListener(TheDarkness instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onMonsterSpawn(CreatureSpawnEvent event){
		if(event.isCancelled()==true){return;}
		
		Entity mobE = event.getEntity();
		String mobName = mobE.getType().getName();
		mobName = plugin.mobMaster.getMobName(mobName);
		mobMD = mobE.getMetadata("Darkness");
		mobLevel = plugin.beaconMaster.getLevel(mobE.getLocation());
		darkLevel = plugin.config.getDarkLevel(mobLevel);
		mobs = (HashSet<String>) darkLevel.getMobs().keySet();
		
		if(darkLevel.getMobs().containsKey(mobName)){
			mobSettings = darkLevel.getMobs().get(mobName);
		}else{
			plugin.logSevere("no mob settings for "+mobName+" on level "+mobLevel+" can be found.");
			return;
		}
		
		if(mobs.contains("ALL")){
			mobSettings = darkLevel.getMobs().get("ALL");
			chance = mobSettings.getIntSetting(SettingEnum.MOBSPAWNCHANCE);
			
			if(passPercentChance(chance)){
				return;
			}else{
				event.setCancelled(true);
				return;
			}
		}
		
		//if mobs does not contain "ALL"
		if(mobSettings.containsSetting(SettingEnum.MOBSPAWNCHANCE)){
			chance = mobSettings.getIntSetting(SettingEnum.MOBSPAWNCHANCE);
		}
		else{
			chance = 100;
		}
		

		
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
			mobSettings = plugin.config.getDarkLevel(mobLevel).getMobs().get(mobName);
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
	
	@EventHandler
	public void onMobFireTick(EntityCombustEvent event){
		plugin.debug("entity combust event");
		if(event.getEntity().hasMetadata("Darkness")){
			event.setCancelled(true);
			plugin.debug("canceling fire event");
		}
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
}
