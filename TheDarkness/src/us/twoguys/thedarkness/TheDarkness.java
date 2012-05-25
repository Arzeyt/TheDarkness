package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import us.twoguys.thedarkness.beacon.*;
import us.twoguys.thedarkness.commands.*;
import us.twoguys.thedarkness.listeners.*;
import us.twoguys.thedarkness.schematics.*;
import us.twoguys.thedarkness.visualization.*;
import us.twoguys.thedarkness.mechanics.*;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	public BeaconMaster beaconMaster = new BeaconMaster(this);
	private BeaconPersister beaconPersister = new BeaconPersister(this);
	public BeaconPlayerDataMaster beaconPlayerDataMaster = new BeaconPlayerDataMaster(this);
	private BeaconPlayerDataPersister beaconPlayerDataPersister = new BeaconPlayerDataPersister(this);
	public VisualizerCore visualizerCore = new VisualizerCore(this);
	public SchematicHandler schematic = new SchematicHandler(this);
	public LocationCheckScheduler locCheck = new LocationCheckScheduler(this);
	public BeaconListenerMaster beaconListenerMaster = new BeaconListenerMaster(this);
	//Listeners
	
	
	public void onEnable(){
		config.loadConfiguration();
		
		beaconPersister.load();
		beaconPlayerDataPersister.load();
		
		loadCommandExecutors();
		loadListeners();
		
		locCheck.checkPlayerLocations();
		
		log("enabled");
		
	}
	
	public void onDisable(){
		
		beaconPersister.save();
		beaconPlayerDataPersister.save();
		log("disabled");
	}

	public void log(String message){
		logger.info("[TheDarkness] " + message);
	}
	
	public void debug(String message){
		logger.info("[TheDarkness DEBUG] " + message);
	}
	
	public void logSevere(String message){
		logger.severe("[TheDarkness] " + message);
	}
	
	public void sendMessage(CommandSender sender, String msg){
		sender.sendMessage(ChatColor.GRAY + "[The Darkness] " + ChatColor.WHITE + msg);
	}
	
	public void sendMessage(Player player, String msg){
		player.sendMessage(ChatColor.GRAY + "[The Darkness] " + ChatColor.WHITE + msg);
	}
	
	public void loadListeners(){
		PluginManager pm = this.getServer().getPluginManager();
		
		BeaconListener beaconListener = new BeaconListener(this);
		pm.registerEvents(beaconListener, this);
		
		log("Listeners Loaded");
	}
	
	public void loadCommandExecutors(){
		GiveBeacon giveBeacon = new GiveBeacon(this);
		this.getCommand("giveBeacon").setExecutor(giveBeacon);
		
		BeaconStats beaconStats = new BeaconStats(this);
		this.getCommand("beaconStats").setExecutor(beaconStats);
		
		Reload reload = new Reload(this);
		this.getCommand("darkreload").setExecutor(reload);
		
		BeaconVision beaconSight = new BeaconVision(this);
		this.getCommand("beaconSight").setExecutor(beaconSight);
		
		CreateBeacon createBeacon = new CreateBeacon(this);
		this.getCommand("createBeacon").setExecutor(createBeacon);
	}
}