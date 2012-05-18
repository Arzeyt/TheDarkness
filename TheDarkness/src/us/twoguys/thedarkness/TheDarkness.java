package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import us.twoguys.thedarkness.beacon.BeaconHandler;
import us.twoguys.thedarkness.beacon.BeaconPersister;
import us.twoguys.thedarkness.beacon.BeaconPlayerDataHandler;
import us.twoguys.thedarkness.beacon.BeaconPlayerDataPersister;
import us.twoguys.thedarkness.commands.BeaconStats;
import us.twoguys.thedarkness.commands.GiveBeacon;
import us.twoguys.thedarkness.listeners.BeaconListener;
import us.twoguys.thedarkness.visualization.VisualizerCore;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	public BeaconHandler beaconHandler = new BeaconHandler(this);
	public BeaconPersister beaconPersister = new BeaconPersister(this);
	public BeaconPlayerDataHandler beaconPlayerDataHandler = new BeaconPlayerDataHandler(this);
	public BeaconPlayerDataPersister beaconPlayerDataPersister = new BeaconPlayerDataPersister(this);
	public VisualizerCore visualizerCore = new VisualizerCore(this);
	
	//Listeners
	
	
	public void onEnable(){
		config.loadConfiguration();
		
		beaconPersister.load();
		beaconPlayerDataPersister.load();
		
		loadCommandExecutors();
		loadListeners();
		
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
		logger.warning("[DEBUG] " + message);
	}
	
	public void logSevere(String message){
		logger.severe("[TheDarkness] " + message);
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
		
	}
}