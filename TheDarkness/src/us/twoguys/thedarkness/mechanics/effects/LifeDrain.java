package us.twoguys.thedarkness.mechanics.effects;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class LifeDrain extends Effect{
	
	/**
	 * 
	 * @Effect: Life Drain
	 * @Description: Damages the player
	 * 
	 **/
	/*Params:
	 *     Mandatory:
	 *         - setting[0]: damage (half hearts)
	 *         - setting[1]: percent chance
	 *     Optional:
	 *         - setting[2]: Frequency (ticks) 
	 *           
	 */

	public LifeDrain(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		
		applyLifeDrain();
	}

	public void applyLifeDrain(){
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			public void run(){
				if(player.isOnline()==false){
					cancelTask();
				}
				int currentLevel = plugin.locCheck.getDarknessLevel(player);
				
				if (currentLevel != level){cancelTask(); return;}
				
				if (passPercentChance(setting.get(1))){
					player.damage(setting.get(0));
				}
			}
			
		}, 0L, getFrequency(2));
	}
}
