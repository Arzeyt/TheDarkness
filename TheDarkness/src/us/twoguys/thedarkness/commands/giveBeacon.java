package us.twoguys.thedarkness.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class giveBeacon implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args[0] == null){
			return false;
		}else if(args[1] == null){
			return false;
		}
		String playerName = args[0];
		int amount = Integer.parseInt(args[1]);
		
		
		return false;
	}

}
