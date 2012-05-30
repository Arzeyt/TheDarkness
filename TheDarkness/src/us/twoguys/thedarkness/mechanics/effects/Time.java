package us.twoguys.thedarkness.mechanics.effects;

import net.minecraft.server.Packet4UpdateTime;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
/**
 * 
 * @author Nick
 *This class is useless unless we can find a way to prevent regular time packets from being received by the player
 */
/*
 * Params:
 * 		Madatory:
 * 			setting[0]: time in ticks
 * 		Optional:
 * 			setting[1]: frequency
 */
public class Time extends Effect{

	public Time(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		applyTime();
	}

	public void applyTime(){
		final CraftPlayer p = (CraftPlayer)player;
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				int currentLevel = plugin.locCheck.getDarknessLevel(player);
				if(currentLevel != level){
					cancelTask();
					return;
				}
				Packet4UpdateTime time = new Packet4UpdateTime(setting.get(0));
				p.getHandle().netServerHandler.sendPacket(time);
				plugin.debug("sent time packet");
			}
			
		}, 0L, getFrequency(1));
	}

	
}
