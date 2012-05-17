package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import us.twoguys.thedarkness.beacon.BeaconHandler;
import us.twoguys.thedarkness.beacon.BeaconPersister;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	public BeaconHandler beaconHandler = new BeaconHandler(this);
	public BeaconPersister beaconPersister = new BeaconPersister(this);
	
	//Listeners
	
	public void onEnable(){
		config.loadConfiguration();
		beaconPersister.load();
		
		log("enabled");
		
	}
	
	public void onDisable(){
		
		beaconPersister.save();
		log("disabled");
	}

	public void log(String message){
		logger.info("[TheDarkness] " + message);
	}
}