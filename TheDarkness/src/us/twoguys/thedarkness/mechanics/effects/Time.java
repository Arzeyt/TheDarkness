package us.twoguys.thedarkness.mechanics.effects;

import java.util.ArrayList;

import net.minecraft.server.Packet4UpdateTime;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.mechanics.PlayerLevelChangeEvent;
/**
 * 
 * @author Nick
 *This class is useless unless we can find a way to prevent regular time packets from being received by the player
 */
/*
 * Params:
 * 		Madatory:
 * 			setting[0]: time in ticks
 * 			setting[1]: transition speed in ticks
 * 		Optional:
 * 			setting[2]: frequency
 */
public class Time implements Listener{
 
	
	TheDarkness plugin;
	int time1, time2, delay, level;
	ArrayList<Integer> time1s, time2s;
	Player player;
	
	public Time(TheDarkness instance) {
		plugin = instance;
		
	}
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent event){
		if(plugin.config.getLevelEffectClasses(event.getLevelTo()).contains(this.getClass())==false){
			plugin.debug("does not contain time");
		}else{
			plugin.debug("Activated Time");
			player = event.getPlayer();
			level = event.getLevelTo();
			time1s = plugin.config.getEffectsSettings(this.getClass(), event.getLevelFrom());
			time2s = plugin.config.getEffectsSettings(this.getClass(), event.getLevelTo());
			time1 = time1s.get(0);
			time2 = time2s.get(0);
			transition();
			setTime();
		}
		
	}
	
	public void transition(){
		
		plugin.debug("transitioning from "+time1+" to "+time2);
		
		final boolean moveForward = time1 < time2 ? true : false;
		
		delay = moveForward ? (time2 - time1)/time2s.get(1) : (time1 - time2)/time2s.get(1);
		plugin.debug(""+delay);
		
		plugin.locCheck.addTask("Transition", plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
	   
	    
			public void run() {
				
				if(moveForward){
					if(time1 < time2){
						time1 = time1+time2s.get(1);
						player.setPlayerTime(time1, false);
						Packet4UpdateTime packet = new Packet4UpdateTime(time1);
						((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
						
					}else if(time1 >= time2s.get(0)){
						plugin.debug("Transition complete------------------------");
						plugin.locCheck.cancelTask("Transition");
					}
				}else{
					if(time1 > time2s.get(0)){
						player.setPlayerTime(time1, false);
						time1 = time1 - time2s.get(1);
						Packet4UpdateTime packet = new Packet4UpdateTime(time1);
						((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
					}else if(time1 <= time2s.get(0)){
						plugin.debug("Transition complete------------------------");
						plugin.locCheck.cancelTask("Transition");
					}
				}
			}
			
		}, 0L, 1));
	}
	
	
	public void setTime(){
		
		plugin.locCheck.addTask("setTime", plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(player.isOnline()==false){
					plugin.locCheck.cancelTask("setTime");
				}
				
				if(plugin.locCheck.getDarknessLevel(player) != level){
					plugin.locCheck.cancelTask("setTime");
					return;
				}
					player.setPlayerTime(time1, false);
					plugin.debug("set time");
			}
			
		}, 0, getFrequency()));
		
	}
	
	private int getFrequency(){
		plugin.debug(time2s.size()+"");
		return time2s.size() < 3 ? plugin.config.getDefaultEffectCheckFreq(level) : time2s.get(2);
	}
}
