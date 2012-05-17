package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import us.twoguys.thedarkness.beacon.BeaconHandler;
import us.twoguys.thedarkness.beacon.BeaconPersister;
import us.twoguys.thedarkness.beacon.BeaconPlayerDataHandler;
import us.twoguys.thedarkness.beacon.BeaconPlayerDataPersister;
import us.twoguys.thedarkness.commands.BeaconStats;
import us.twoguys.thedarkness.commands.GiveBeacon;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	public BeaconHandler beaconHandler = new BeaconHandler(this);
	public BeaconPersister beaconPersister = new BeaconPersister(this);
	public BeaconPlayerDataHandler beaconPlayerDataHandler = new BeaconPlayerDataHandler(this);
	public BeaconPlayerDataPersister beaconPlayerDataPersister = new BeaconPlayerDataPersister(this);
	
	//Listeners
	
	public void onEnable(){
		config.loadConfiguration();
		beaconPersister.load();
		beaconPlayerDataPersister.load();
		
		loadCommandExecutors();
		
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
	
	public void loadCommandExecutors(){
		GiveBeacon giveBeacon = new GiveBeacon(this);
		this.getCommand("giveBeacon").setExecutor(giveBeacon);
		
		BeaconStats beaconStats = new BeaconStats(this);
		this.getCommand("beaconStats").setExecutor(beaconStats);
		
	}
}