package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import us.twoguys.thedarkness.TheDarkness;

/**
 * 
 * @author Nick
 * 
 */

/*
 * setting[1]= chance
 * setting[2]= distance
 * setting[3]= amount
 * 
 * setting[4]= frequency
 */
public class Mob implements Listener{

	TheDarkness plugin;
	String mobName;
	int level;
	int taskID;
	Player player;
	Random random = new Random();
	private ArrayList<Integer> setting;
	int frequency;
	int chance;
	int distance;
	int amount;
	
	public Mob(TheDarkness instance, Player player, int level, String mob){
		this.plugin = instance;
		this.mobName = plugin.mobMaster.getMobName(mob);
		this.player = player;
		this.level = level;
		setting = plugin.config.getMobSettings(mob, level);
		
		if(mobName.equalsIgnoreCase("all")==false){
			this.chance = setting.get(1);
			this.distance = setting.get(2);
			this.amount = setting.get(3);
			this.frequency = getFrequency(4);
			
			applyMob();
		}
	}
	
	public void applyMob(){
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(continueCheck(player)==false){
					Bukkit.getServer().getScheduler().cancelTask(taskID);
					plugin.debug("Cancelled mob spawn task");
				}else{
					if(passPercentChance(chance)==false){
						plugin.debug("did not pass % chance");
						return;
					}
					for(int i = amount; i > 0; i --){
						
						plugin.debug("attempting to spawn mob");
						
						Location loc = plugin.locTools.getRandomGround(player.getLocation(), setting.get(2));
						loc = loc.getBlock().getRelative(0, 1, 0).getLocation();
						
						Entity entity = player.getWorld().spawnCreature(loc, EntityType.fromName(mobName));
						
						FixedMetadataValue md = new FixedMetadataValue(plugin, 1);
						entity.setMetadata("Darkness", md);
					}
				}
			}
			
		}, 0L, frequency);
		
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public int getFrequency(int i){
		return (setting.size() >= i + 1 ? setting.get(i) : plugin.config.getDefaultEffectCheckFreq(level));
	}
	public boolean continueCheck(Player player){
		
		if(player.isOnline()==false){
			return false;
		}
		int currentLevel = plugin.locCheck.getDarknessLevel(player);
		
		if(currentLevel != level){
			return false;
		}
		return true;
		
	}
}
