package us.twoguys.thedarkness.mechanics.mirages;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
/**
 * 
 * @author Nick
 * 
 */
/*
 * Mandatory:
 * 		setting[0] = block Id
 * 		setting[1] = number of blocks to visualize
 * 		setting[2] = radius
 * Optional:
 * 		setting[3] = frequency
*/
public class Spread extends Mirage{

	ArrayList<Location> locs = new ArrayList<Location>();
	ArrayList<Integer> replaceBlocks = new ArrayList<Integer>();
	
	public Spread(TheDarkness instance, Player player, int level) {
		super(instance, player, level);
		setting = plugin.config.getMirageSettings(this.getClass(), level);
		applySpread();
		
	}

	private void applySpread() {
		
		taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(continueCheck(player)){
					for(int i = 0; i < setting.get(1); i ++){
						plugin.debug("Spread");
						Location loc = plugin.locTools.getRandomGround(player.getLocation(), setting.get(2));
						
						if(loc.getWorld().getBlockAt(loc).getType()!=Material.LEAVES){
							locs.add(loc);
							Material mat = Material.getMaterial(setting.get(0));
							plugin.visualizerCore.visualizeBlock(player, loc, mat);
						}
					}
				}else{
					for(Location loc : locs){
						plugin.visualizerCore.revertChunk(player, loc);
						
					}
					cancelTask();
				}
			}
			
		}, 0L, getFrequency(4));
	}
	
	
	
	

}
