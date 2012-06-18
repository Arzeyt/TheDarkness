package us.twoguys.thedarkness.mechanics.mobs;

import java.util.HashMap;

import org.bukkit.Bukkit;

import us.twoguys.thedarkness.TheDarkness;

public class MobMaster {
	private HashMap<String, Integer> taskIds = new HashMap<String, Integer>();
	TheDarkness plugin; 
	
	public MobMaster(TheDarkness instance){
		plugin = instance;
	}
	
	public Integer getTaskId(String name){
		return taskIds.get(name);
	}
	
	public void cancelTask(String name){
		Bukkit.getServer().getScheduler().cancelTask(taskIds.get(name));
	}
	
	public void addTask(String name, int taskId){
		if(taskIds.containsKey(name)){
			cancelTask(name);
		}else{
			taskIds.put(name, taskId);
		}
	}
	public String getMobName(String mobName){
		if(mobName.equalsIgnoreCase("all")){
			return new String("ALL");
		}else if(mobName.equalsIgnoreCase("mooshroomcow") || mobName.equalsIgnoreCase("mooshroom")
				|| mobName.equalsIgnoreCase("mooshroom_cow")){
			return new String("MooshroomCow");
		}else if(mobName.equalsIgnoreCase("Chicken")){
			return new String("Chicken");
		}else if(mobName.equalsIgnoreCase("PigZombie") || mobName.equalsIgnoreCase("ZombiePigman")
				|| mobName.equalsIgnoreCase("Pig_Zombie") || mobName.equalsIgnoreCase("Zombie_Pigman")){
			return new String("PigZombie");
		}else if(mobName.equalsIgnoreCase("MagmaCube") || mobName.equalsIgnoreCase("Magma_Cube")){
			return new String("MagmaCube");
		}else if(mobName.equalsIgnoreCase("CaveSpider") || mobName.equalsIgnoreCase("Cave_Spider")){
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
		}else if(mobName.equalsIgnoreCase("Silverfish") || mobName.equalsIgnoreCase("Silver_fish")){
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
		}else if(mobName.equalsIgnoreCase("Ocelot") || mobName.equalsIgnoreCase("Cat") || mobName.equalsIgnoreCase("Ozelot")){
			return new String("Ocelot");
		}else if(mobName.equalsIgnoreCase("IronGolem") || mobName.equalsIgnoreCase("Iron_Golem")){
			return new String("IronGolem");
		}else if(mobName.equalsIgnoreCase("SnowGolem") || mobName.equalsIgnoreCase("Snow_Golem")){
			return new String("SnowGolem");
		}else if(mobName.equalsIgnoreCase("EnderDragon") || mobName.equalsIgnoreCase("Ender_Dragon")){
			return new String("EnderDragon");
		}else if(mobName.equalsIgnoreCase("Giant")){
			return new String("Giant");
		}else{
			plugin.logSevere("Mob "+mobName+" does not exist. Defaulting to Chicken ");
			return new String("Chicken");
		}
	}


}
