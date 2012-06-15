package us.twoguys.thedarkness.mechanics.effects;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.twoguys.thedarkness.TheDarkness;

public class Potion extends Effect{

	/**
	 * @effect: PotionEffect
	 * @Description: Applys a potion effect to the player
	 */
	/*
	 * Params:
	 * 		Mandatory:
	 * 			- setting[0]: potion id
	 * 			- setting[1]: % chance
	 * 			- setting[2]: duration
	 * 			- setting[3]: strength
	 * 		Optional:
	 * 			- setting[4]: frequency (ticks)
	 */
	public Potion(TheDarkness instance, Player player, int level, ArrayList<Integer> settings){
		super(instance, player, level);
		this.setting = settings;
		applyPotion();
	}
	
	private void applyPotion() {
		
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(player.isOnline()==false){
					cancelTask();
				}
				int currentLevel = plugin.locCheck.getDarknessLevel(player);
				
				if(currentLevel != level){
					if (currentLevel == 0){
						player.removePotionEffect(PotionEffectType.getById(setting.get(0)));
					}
					cancelTask();
					return;
				}
				
				if(passPercentChance(setting.get(1))){
					if ((setting.get(0) == PotionEffectType.REGENERATION.getId() && player.getHealth() < 20) || setting.get(0) != PotionEffectType.REGENERATION.getId())
					player.addPotionEffect(new PotionEffect(PotionEffectType.getById(setting.get(0)), setting.get(2)*20, setting.get(3) - 1));
				}
			}
			
		}, 0L, getFrequency(4));
	}
}
