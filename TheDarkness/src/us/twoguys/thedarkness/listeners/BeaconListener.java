package us.twoguys.thedarkness.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BeaconListener implements Listener{

	@EventHandler
	public void onPlayerBlockHit(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
	}
}
