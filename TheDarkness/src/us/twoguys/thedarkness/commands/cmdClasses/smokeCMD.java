package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.visualization.Smoke;

public class smokeCMD {

	TheDarkness plugin;
	int taskID;
	
	public smokeCMD(TheDarkness instance){
		plugin=instance;
	}
	
	public void applySmoke(Player player, final Location loc, final int amount){
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				for(int i = 0; i < amount; i++){
					Smoke smoke = new Smoke(plugin, loc);
					smoke.expandBasic(loc);
				}
			}
			
		}, 0, 20);
	}
}
