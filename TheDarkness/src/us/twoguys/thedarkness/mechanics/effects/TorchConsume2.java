package us.twoguys.thedarkness.mechanics.effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.visualization.Smoke;

public class TorchConsume2 implements Listener{
	
	TheDarkness plugin;
	
	public TorchConsume2(TheDarkness instance){
		plugin = instance;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		final Location loc = block.getLocation();
		
		if (block.getType().equals(Material.TORCH)){
			int level = plugin.config.getLevel(plugin.beaconMaster.getDistanceFromNearestBeacon(block.getLocation()));
			int time = plugin.config.getLevelTorchConsumeTime(level);
			
			plugin.debug("placed torch");
			
			if (time != 123456789){
				
				plugin.debug("Torch will be consumed in " + time + "seconds");
				
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run(){
						consumeTorch(loc);
					}
				}, time*20);
			}
		}
	}
	
	public void consumeTorch(Location loc){
		if (loc.getBlock().getType().equals(Material.TORCH)){
			loc.getBlock().setType(Material.AIR);
			Smoke smoke = new Smoke(plugin, loc);
			smoke.expand();
			plugin.debug("Set torch to AIR");
		}
	}
}
