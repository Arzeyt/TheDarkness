package us.twoguys.thedarkness.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconData;

public class BeaconListener implements Listener{

	TheDarkness plugin;
	int c = 1;
	
	public BeaconListener(TheDarkness instance){
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerBlockHit(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if (block == null){return;}
		/*
		if(c == 1){
			plugin.schematic.loadSchematic();
			c = 2;
			
		}else if(c==2){
			c = 1;
		}
		*/
		
		if(plugin.beaconPlayerDataMaster.canCreateBeacon(player, true)){
			BeaconData bd = new BeaconData(block.getLocation());
			plugin.beaconMaster.createBeacon(player, bd);
		}
	}
}
