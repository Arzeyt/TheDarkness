package us.twoguys.thedarkness.commands;

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
		
		
		try{if(args[0]==null){return false;} //if the player never entered in an arg, then args does not exist and throws an error.
		}catch(Exception e){return generalUsage(player);}
		
		if(args[0].equalsIgnoreCase("stats")){
			return stats(player);
			
		}else if(args[0].equalsIgnoreCase("beaconvision") || args[0].equalsIgnoreCase("vision")){
			return beaconVision(player);
			
		}else if(args[0].equalsIgnoreCase("createbeacon") || args[0].equalsIgnoreCase("create")){
			return createBeacon(player);
			
		}else if(args[0].equalsIgnoreCase("give")){
			if (args.length == 3){
				return giveEssence(args[1], args[2]);
			}else{
				return giveEssenceUsage(player);
			}
			
		}else if(args[0].equalsIgnoreCase("reload")){
			reload(player);
	
		}else if(args[0].equalsIgnoreCase("paste")){
			if(args.length < 2){
				return pasteSchematicUsage(player);
			}
			pasteSchematic(player, player.getLocation(), args[1]);
		}
		return true;
	}
	private boolean generalUsage(Player player){
		//ToDo: add some code :)
		return true;
	}
	
	private boolean stats(Player player){
		try{
			int points = plugin.beaconPlayerDataMaster.getData((Player)player).getBeaconPoints();
			player.sendMessage("Dark Essence: "+ChatColor.GREEN+points);
			return true;
		}catch(NullPointerException e){
			player.sendMessage(ChatColor.RED+"You have no dark essence");
			return true;
		}
	}
	private boolean beaconVision(Player player){
		plugin.visualizerCore.visualizeBeacons(player);
		return true;
	}
	
	private boolean createBeacon(Player player){
		plugin.beaconListenerMaster.setString(player, "beaconPlace");
		plugin.sendMessage(player, "Select a location to place the beacon");
		return true;
	}
	
	private boolean giveEssence(String player, String quantity){

		int amount = Integer.parseInt(quantity);
		
		BeaconPlayerData beaconPlayerData = plugin.beaconPlayerDataMaster.getData(Bukkit.getPlayer(player));
		beaconPlayerData.incrementPoints(amount);
		
		plugin.sendMessage(Bukkit.getPlayer(player), ChatColor.GREEN + "You have given " + player + " " + amount + " dark essence");
	
		Bukkit.getServer().getPlayer(player).sendMessage(ChatColor.GREEN+"You have recieved "+amount+" dark essence");
		return true;
	}
	
	private boolean giveEssenceUsage(Player player){
		plugin.sendMessage(player, "/theDarkness give <playerName> <DarkEssence quantity>");
		return true;
	}
	
	private boolean reload(Player player){
		plugin.config.loadConfiguration();
		plugin.sendMessage(player, "Successfully reloaded config!");
		return true;
	}
	
	private boolean pasteSchematic(Player player, Location loc, String schematicName){
		try {
			plugin.schematicHandler.paste(player, loc, schematicName);
		} catch (InvalidSchematicException e) {
			plugin.debug(schematicName+" is not a valid schematic");
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean pasteSchematicUsage(Player player){
		plugin.sendMessage(player, "/theDarkess paste <schematic name>");
		return true;
	}
	
}
