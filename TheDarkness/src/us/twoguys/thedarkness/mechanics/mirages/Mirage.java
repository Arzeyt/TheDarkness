package us.twoguys.thedarkness.mechanics.mirages;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.schematics.SchematicObject;

/*
 * Params:
 * 		Mandatory:
 * 			- setting[0]: mirage name
 * 			- setting[1]: % chance
 * 			- setting[2]: duration
 * 		Optional:
 * 			- setting[3]: frequency (ticks)
 * */
public class Mirage {


	TheDarkness plugin;
	
	protected Player player;
	protected int level;
	protected int taskId;
	protected ArrayList<Integer> setting;
	
	protected Random random = new Random();
	
	public Mirage(TheDarkness instance, Player player, int level){
		plugin = instance;
		this.player = player;
		this.level = level;
		
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	
	public int getFrequency(int i){
		return (setting.size() >= i + 1 ? setting.get(i) : plugin.config.getDefaultEffectCheckFreq(level));
	}
	
	public void cancelTask(){
		plugin.getServer().getScheduler().cancelTask(taskId);
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


