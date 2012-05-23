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
	int c = 0; //set to 0 to disable
	
	public BeaconListener(TheDarkness instance){
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerBlockHit(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if (block == null){return;}
		
		if(c == 1){
			plugin.schematic.loadSchematic();
			c = 2;
			
		}else if(c==2){
			plugin.schematic.paste(player, block.getLocation());
			c = 1;
		}else if(plugin.beaconListenerMaster.getPlayerString(player).equalsIgnoreCase("beaconPlace")){
			if(plugin.beaconPlayerDataMaster.canCreateBeacon(player, true)){
				BeaconData bd = new BeaconData(block.getLocation());
				plugin.beaconMaster.createBeacon(player, bd);
				plugin.beaconListenerMaster.setString(player, "null");
				plugin.sendMessage(player, "You have created a beacon");
			}else{
				plugin.sendMessage(player, "You dont have enough dark essence. You need at least "+plugin.config.getBeaconCost());
			}
			
		}
		
	}
}
