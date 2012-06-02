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
 * 			setting[1]: transition speed in ticks
 * 		Optional:
 * 			setting[2]: frequency
 */
public class Time extends Effect{

	//long delay;
	long time;
	int[] tasks = new int[3];
	
	int currentLevel;
	boolean hasTransitioned=false;
	boolean transitionActive = false;
	boolean setTimeActive = false;
	
	public Time(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		this.setting = plugin.config.getEffectsSettings(this.getClass(), level);
		applyTime();
	}

	public void applyTime(){
		
		/*
		long delayTemp = (player.getWorld().getTime()-setting.get(0))/setting.get(1);
		long delayTemp2 = (setting.get(0)-player.getWorld().getTime())/setting.get(1);
		delay = delayTemp < 0 ? delayTemp2 : delayTemp;
		*/
		time = plugin.timeMaster.getTime(player);
		
		tasks[0] = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
		
			public void run() {
				
				if(player.isOnline()==false){
					plugin.getServer().getScheduler().cancelTask(tasks[0]);
				}
				currentLevel = plugin.locCheck.getDarknessLevel(player);
				
				if(currentLevel != level){
					player.setPlayerTime(player.getWorld().getTime(), true);
					plugin.getServer().getScheduler().cancelTask(tasks[0]);
					return;
				}
				if(transitionActive == false){
					if(hasTransitioned==false){
						transition();
					}
				}
				if(setTimeActive == false && hasTransitioned == true){
					
					setTime();
				}
				
			}
			
		}, 0L, getFrequency(2));
	}
	
	public void transition(){
		
		
		transitionActive = true;
		time=plugin.timeMaster.getTime(player);
		plugin.debug("transitioning from "+time+" to "+setting.get(0));
		
		final boolean moveForward = time < setting.get(0) ? true : false;
		
		tasks[1] = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
	   
	    
			public void run() {
				
				if(moveForward){
					if(time < setting.get(0)){
						time = time+setting.get(1);
						player.setPlayerTime(time, false);
						Packet4UpdateTime packet = new Packet4UpdateTime(time);
						((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
						
					}else if(time >= setting.get(0)){
						plugin.debug("Transition complete------------------------");
						hasTransitioned=true;
						plugin.timeMaster.setTime(player, setting.get(0));
						plugin.getServer().getScheduler().cancelTask(tasks[1]);
					}
				}else{
					if(time > setting.get(0)){
						time = time - setting.get(1);
						player.setPlayerTime(time, false);
						Packet4UpdateTime packet = new Packet4UpdateTime(time);
						((CraftPlayer)player).getHandle().netServerHandler.sendPacket(packet);
					}else if(time <= setting.get(0)){
						plugin.debug("Transition complete------------------------");
						hasTransitioned=true;
						plugin.timeMaster.setTime(player, setting.get(0));
						plugin.getServer().getScheduler().cancelTask(tasks[1]);
					}
				}
			}
			
		}, 0L, 1);
	}
	
	
	public void setTime(){
		
		setTimeActive = true;
		tasks[2] = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(player.isOnline()==false){
					plugin.getServer().getScheduler().cancelTask(tasks[2]);
				}
				
				if(currentLevel != level){
					player.setPlayerTime(player.getWorld().getTime(), true);
					plugin.getServer().getScheduler().cancelTask(tasks[2]);
					return;
				}
					plugin.debug("set time");
					player.setPlayerTime(setting.get(0), false);

			}
			
		}, 0L, 20);
		
	}
}
