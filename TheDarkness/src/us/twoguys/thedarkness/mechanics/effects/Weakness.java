package us.twoguys.thedarkness.mechanics.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.twoguys.thedarkness.TheDarkness;

public class Weakness extends Effect{

	/**
	 * @effect: LifeDrain
	 * @Description: weakens the player
	 */
	/*
	 * Params:
	 * 		Mandatory:
	 * 			- setting[0]: % chance
	 * 			- setting[1]: strength
	 * 			- setting[2]: duration
	 * 		Optional:
	 * 			- setting[3]: frequency (ticks)
	 */
	public Weakness(TheDarkness instance, Player player, int level){
		super(instance, player, level);
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		applyWeakness();
	}
	
	private void applyWeakness() {
		
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				int currentLevel = plugin.locCheck.getDarknessLevel(player);
				
				if(currentLevel != level){
					cancelTask();
				}
				if(passPercentChance(setting.get(0))){
					player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, setting.get(1), setting.get(2)*20));
				}
			}
			
		}, 0L, getFrequency(2));
	}
}
