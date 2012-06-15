package us.twoguys.thedarkness.mechanics;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import us.twoguys.thedarkness.TheDarkness;

public class Message implements Listener{

	TheDarkness plugin;
	ArrayList<String> messages;
	
	public Message(TheDarkness instance){
		plugin=instance;

	}
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent event){
		Player player = event.getPlayer();
		messages = plugin.config.getLevelMessage(event.getLevelTo());
		
		if(event.getLevelFrom() < event.getLevelTo()){
			plugin.sendMessage(player, messages.get(0));
		}else{
			try{
				plugin.sendMessage(player, messages.get(1));
			}catch(Exception e){
				plugin.sendMessage(player, messages.get(0));
			}
		}
	}
}
