package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.InsufficientPointsException;
import us.twoguys.thedarkness.player.DarkPlayer;

public class GiveCMD {

	TheDarkness plugin;
	
	public GiveCMD(TheDarkness instance){
		this.plugin=instance;
	}
	public boolean giveEssence(Player giver, String target, String quantity) throws InsufficientPointsException{
		Player reciever = Bukkit.getPlayer(target);
		int amount = Integer.parseInt(quantity);
		
		DarkPlayer giverData = plugin.playerMaster.getDarkPlayer(giver.getName());
		DarkPlayer recieverData = plugin.playerMaster.getDarkPlayer(reciever.getName());
		
		try{
			giverData.incrementPoints(-amount);
		}catch(InsufficientPointsException e){
			e.getMessage();
			plugin.sendMessage(giver, ChatColor.RED+"you do not have enough points");
		}
		
		recieverData.incrementPoints(amount);
		
		plugin.sendMessage(giver, ChatColor.GREEN + "You have given " + reciever.getName() + " " + amount + " dark essence");
	
		plugin.sendMessage(reciever, ChatColor.GREEN+"You have recieved "+amount+" dark essence");
		return true;
	}
	
	public String toString(){
		return new String("/td give <player name> <amount> "+ChatColor.GRAY+"transfers your points to a player");
	}
}
