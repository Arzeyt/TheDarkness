package us.twoguys.thedarkness.mechanics.effects;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet70Bed;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

/*
 * Madatory:
 * 		setting[0] = weather true / false (1/2)
 * Optional:
 * 		setting[1] = frequency
 */
public class Precipitation extends Effect{

	public Precipitation(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		
		applyPercipitation();
	}

	public void applyPercipitation(){
	    final Packet70Bed weatherPacket = new Packet70Bed();
	    weatherPacket.b = (setting.get(0));
	    
	    final Packet70Bed clearWeatherPacket = new Packet70Bed();
	    clearWeatherPacket.b = (2);
	
	    final EntityPlayer p = ((CraftPlayer)player).getHandle();
	    
		taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			@Override
			public void run() {
				if(continueCheck(player)){
					plugin.debug("sent weather packet");
					p.netServerHandler.sendPacket(weatherPacket);
				}else{
					plugin.debug("Sent clear weather packet");
					p.netServerHandler.sendPacket(clearWeatherPacket);
					cancelTask();
				}
				
			}
			
		}, 0L, getFrequency(1));
	}
}
