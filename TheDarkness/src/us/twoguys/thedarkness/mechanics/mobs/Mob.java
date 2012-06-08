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
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public int getFrequency(int i){
		return (setting.size() >= i + 1 ? setting.get(i) : plugin.config.getDefaultEffectCheckFreq(level));
	}
}
