package us.twoguys.thedarkness.commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.beacon.BeaconPlayerData;
import us.twoguys.thedarkness.beacon.InsufficientPointsException;
import us.twoguys.thedarkness.beacon.PasteSchematicCMD;
import us.twoguys.thedarkness.schematics.InvalidSchematicException;
import us.twoguys.thedarkness.schematics.SchematicHandler;

public class CommandMaster implements CommandExecutor{

	TheDarkness plugin;
	boolean isPlayer;
	HashMap<Player, SchematicHandler> schematics = new HashMap<Player, SchematicHandler>();

	public CommandMaster(TheDarkness instance){
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if(!(sender instanceof Player) && (!args[0].equalsIgnoreCase("reload") || !args[0].equalsIgnoreCase("give"))){
			sender.sendMessage("You must be logged in to do that");
			return false;
		}else if(!(sender instanceof Player)){
			isPlayer = false;
		}
		
		Player player = (Player)sender;
		
		
		try{if(args[0]==null){
			return true;
			} //if the player never entered in an arg, then args does not exist and throws an error.
		}catch(Exception e){
			return usage(player);
		}
		
		if(args[0].equalsIgnoreCase("stats")){
			StatsCMD cmd = new StatsCMD(plugin);
			return cmd.stats(player);
			
		}else if(args[0].equalsIgnoreCase("beaconvision") || args[0].equalsIgnoreCase("vision")){
			BeaconVisionCMD cmd = new BeaconVisionCMD(plugin);
			cmd.beaconVision(player);
			
		}else if(args[0].equalsIgnoreCase("createbeacon") || args[0].equalsIgnoreCase("create")){
			CreateBeaconCMD cmd = new CreateBeaconCMD(plugin);
			cmd.createBeacon(player);
			
		}else if(args[0].equalsIgnoreCase("give")){
			GiveCMD cmd = new GiveCMD(plugin);
			if (args.length == 3){
				try {
					return cmd.giveEssence(player, args[1], args[2]);
				} catch (InsufficientPointsException e) {
					e.printStackTrace();
				}
			}else{
				plugin.sendMessage(player, cmd.toString());
				return true;
			}
			
		}else if(args[0].equalsIgnoreCase("reload")){
			reload(player);
	
		}else if(args[0].equalsIgnoreCase("paste")){
			PasteSchematicCMD cmd = new PasteSchematicCMD(plugin);
			if(args.length < 2){
				plugin.sendMessage(player, cmd.toString());
			}
			cmd.pasteSchematic(player, player.getLocation(), args[1]);
		}
		return true;
	}
	
	
	private boolean reload(Player player){
		plugin.config.loadConfiguration();
		plugin.sendMessage(player, "Successfully reloaded config!");
		return true;
	}
	

	
	private boolean usage(Player player){
		plugin.sendMessage(player, ChatColor.DARK_BLUE+"---------- Commands ----------");
		player.sendMessage("/td vision "+ChatColor.GRAY+"Reveals the nearest beacon");
		player.sendMessage("/td create "+ChatColor.GRAY+"Creates a beacon at the next selected block");
		player.sendMessage("/td give <player name> <amount> "+ChatColor.GRAY+" transfers dark essence to a player");
		player.sendMessage("/td stats "+ChatColor.GRAY+"Dispalys useful information");
		return true;
	}
	
}
