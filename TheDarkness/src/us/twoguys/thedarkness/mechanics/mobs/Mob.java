package us.twoguys.thedarkness.mechanics.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import us.twoguys.thedarkness.TheDarkness;

/**
 * 
 * @author Nick
 * 
 */
/*
 * 
 */
public class Mob{

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
	
	public String matchMobName(String mobName){
		if(mobName.equalsIgnoreCase("mooshroomcow") || mobName.equalsIgnoreCase("mooshroom")){
			return new String("MooshroomCow");
		}else if(mobName.equalsIgnoreCase("Chicken")){
			return new String("Chicken");
		}else if(mobName.equalsIgnoreCase("PigZombie") || mobName.equalsIgnoreCase("ZombiePigman")){
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
		}else if(mobName.equalsIgnoreCase("Ghast")){
			return new String("Ghast");
		}else if(mobName.equalsIgnoreCase("Enderman")){
			return new String("Enderman");
		}else if(mobName.equalsIgnoreCase("Silverfish")){
			return new String("Silverfish");
		}else if(mobName.equalsIgnoreCase("Blaze")){
			return new String("Blaze");
		}else if(mobName.equalsIgnoreCase("Pig")){
			return new String("Pig");
		}else if(mobName.equalsIgnoreCase("Sheep")){
			return new String("Sheep");
		}else if(mobName.equalsIgnoreCase("Cow")){
			return new String("Cow");
		}else if(mobName.equalsIgnoreCase("Squid")){
			return new String("Squid");
		}else if(mobName.equalsIgnoreCase("Wolf") || mobName.equalsIgnoreCase("Dog")){
			return new String("Wolf");
		}else if(mobName.equalsIgnoreCase("Vilager")){
			return new String("Villager");
		}else if(mobName.equalsIgnoreCase("Ocelot") || mobName.equalsIgnoreCase("Cat")){
			return new String("Ocelot");
		}else if(mobName.equalsIgnoreCase("IronGolem")){
			return new String("IronGolem");
		}else if(mobName.equalsIgnoreCase("SnowGolem")){
			return new String("SnowGolem");
		}else if(mobName.equalsIgnoreCase("EnderDragon")){
			return new String("EnderDragon");
		}else{
			plugin.logSevere("Mob "+mobName+" does not exist. Defaulting to Chicken ");
			return new String("Chicken");
		}
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public int getFrequency(int i){
		return (setting.size() >= i + 1 ? setting.get(i) : plugin.config.getDefaultEffectCheckFreq(level));
	}
}
