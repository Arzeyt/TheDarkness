package us.twoguys.thedarkness.mechanics.mirages;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

/*
 * Mandatory:
 * 		setting[0] = block Id
 * 		setting[1] = blockData
 * 		setting[2] = rate
 * 		setting[3] = radius
 * Optional:
 * 		setting[4] = frequency
*/
public class Spread extends Mirage{

	ArrayList<Location> locs = new ArrayList<Location>();
	
	public Spread(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		setting = plugin.config.getMirageSettings(this.getClass(), level);
		spread();
	}

	private void spread() {
		
		taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(continueCheck(player)){
					Location loc = plugin.locTools.getRandomGround(player.getLocation(), setting.get(3));
					locs.add(loc);
					//plugin.visualizerCore.visualizeBlock(player, loc, setting.get(0), setting.get(1));
					
				}else{
					for(Location loc : locs){
						plugin.visualizerCore.revertChunk(player, loc);
					}
				}
			}
			
		}, 0L, getFrequency(4));
	}
	
	
	
	

}
