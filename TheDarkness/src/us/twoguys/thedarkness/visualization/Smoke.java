package us.twoguys.thedarkness.visualization;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;

import us.twoguys.thedarkness.TheDarkness;

public class Smoke {

	TheDarkness plugin;
	Location loc;
	int taskID;
	
	public Smoke(TheDarkness instance, Location loc){
		this.plugin = instance;
		this.loc = loc;
	}
	
	public void expandBasic(Location loc){
		/*
	      loc.getWorld().playEffect(loc, Effect.SMOKE, 4);
	      loc.getWorld().playEffect(loc, Effect.SMOKE, 1);
	      loc.getWorld().playEffect(loc, Effect.SMOKE, 3);
	      loc.getWorld().playEffect(loc, Effect.SMOKE, 5);
	      loc.getWorld().playEffect(loc, Effect.SMOKE, 7);
	      */
	      loc.getWorld().playEffect(loc, Effect.STEP_SOUND, 1);
	}
	
	public void expand(){
		expandBasic(loc);
		expandBasic(loc.getBlock().getRelative(0,1,0).getLocation());
	}
	
	public void expandSpam(final int recursions, int delay, int frequency){
		
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			int counter = recursions;
			
			public void run() {
				if(counter>0==false){
					Bukkit.getServer().getScheduler().cancelTask(taskID);
					return;
				}else{
					expand();
					counter--;
				}
			}
			
		}, delay, frequency);
	}
	
	
}
