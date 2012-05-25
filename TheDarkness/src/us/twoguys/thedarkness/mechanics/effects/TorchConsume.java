package us.twoguys.thedarkness.mechanics.effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import us.twoguys.thedarkness.TheDarkness;

public class TorchConsume implements Listener{
	
	TheDarkness plugin;
	
	public TorchConsume(TheDarkness instance){
		plugin = instance;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		final Location loc = block.getLocation();
		
		if (block.getType().equals(Material.TORCH)){
			int level = plugin.config.getLevel(plugin.beaconMaster.distanceFromNearestBeacon(block.getLocation()));
			int time = plugin.config.getLevelTorchConsumeTime(level);
			
			plugin.debug("placed torch");
			
			if (time != 123456789){
				
				plugin.debug("Torch will DIE in " + time + "seconds");
				
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					
					public void run(){
						plugin.debug("Delayed task begin");
						if (loc.getBlock().getType().equals(Material.TORCH)){
							loc.getBlock().setType(Material.AIR);
							plugin.debug("Set torch to AIR");
						}
					}
					
				}, time*20);
			}
		}
	}
}
