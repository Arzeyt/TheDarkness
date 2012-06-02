package us.twoguys.thedarkness.mechanics.effects;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class Effect {

	TheDarkness plugin;
	
	protected Player player;
	protected int level;
	protected int taskId;
	protected ArrayList<Integer> setting;
	
	protected Random random = new Random();
	
	public Effect(TheDarkness instance, Player player, int level){
		plugin = instance;
		this.player = player;
		this.level = level;
	}
	
	public boolean passPercentChance(int i){
		return (random.nextInt(100) + 1 <= i ? true : false);
	}
	/**
	 * 
	 * @param i - position in the setting array of the frequency
	 * @return frequency in ticks
	 */
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
