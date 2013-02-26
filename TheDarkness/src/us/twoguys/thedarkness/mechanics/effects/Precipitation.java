package us.twoguys.thedarkness.mechanics.effects;

import net.minecraft.server.v1_4_6.Packet70Bed;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_4_6.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

/*
 * Mandatory:
 * 		setting[0] = weather true / false (1/2)
 * 		setting[1] = rate
 * Optional:
 * 		setting[2] = frequency
 */
public class Precipitation extends Effect{

	int rate;
	int i;
	
	public Precipitation(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		rate = setting.get(1);
		
		applyPercipitation();
	}

	public void applyPercipitation(){
	
	    final CraftPlayer p = ((CraftPlayer)player);
	   
		taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
		
			
			@Override
			public void run() {
				if(continueCheck(player)){
					if(i%rate == 0){
						plugin.debug("sending rainy weather packet");
						p.getHandle().playerConnection.sendPacket(new Packet70Bed(1, 0));
					}
					i++;
				}else{
					plugin.debug("Sending clear weather packet");
					p.getHandle().playerConnection.sendPacket(new Packet70Bed(2, 0));
					cancelTask();
					
				}
				
			}
			
		}, 0L, getFrequency(2));
	}
}
