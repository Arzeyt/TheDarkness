package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.Random;

import javax.naming.InvalidNameException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

/**
 * 
 * @author Nick
 * 
 */
/*
 * 
 */
public class Mob {

	TheDarkness plugin;
	String mobName;
	int level;
	int taskID;
	Player player;
	Random random = new Random();
	protected ArrayList<Integer> setting;
	
	public Mob(TheDarkness instance, Player player, String mobName, int level){
		this.mobName = mobName;
		this.level = level;
		this.player = player;
	}
	
	public void applyMob(){
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				
			}
			
		}, 0L, 123456789);
		
	}
	
	public String matchMobName(String mobName) throws InvalidNameException{
		if(mobName.equalsIgnoreCase("mooshroomcow") || mobName.equalsIgnoreCase("mooshroom")){
			return new String("MooshroomCow");
		}else if(mobName.equalsIgnoreCase("Chicken")){
			return new String("Chicken");
		}else if(mobName.equalsIgnoreCase("PigZombie")){
			return new String("PigZombie");
		}else if(mobName.equalsIgnoreCase("MagmaCube")){
			return new String("MagmaCube");
		}else if(mobName.equalsIgnoreCase("CaveSpider")){
			return new String("CaveSpider");
		}else if(mobName.equalsIgnoreCase("Creeper")){
			return new String("Creeper");
		}else if(mobName.equalsIgnoreCase("Skeleton")){
			return new String("Skeleton");
		}else if(mobName.equalsIgnoreCase("Spider")){
			return new String("Spider");
		}else if(mobName.equalsIgnoreCase("Zombie")){
			return new String("Zombie");
		}else if(mobName.equalsIgnoreCase("Slime")){
			return new String("Slime");
		}else{
			throw new InvalidNameException("Mob "+mobName+" does not exist");
		}
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public int getFrequency(int i){
		return (setting.size() >= i + 1 ? setting.get(i) : plugin.config.getDefaultEffectCheckFreq(level));
	}
}
