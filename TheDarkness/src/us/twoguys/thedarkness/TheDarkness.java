package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.malikk.shield.Shield;
import com.malikk.shield.ShieldAPI;

import us.twoguys.thedarkness.beacon.*;
import us.twoguys.thedarkness.beacon.listeners.*;
import us.twoguys.thedarkness.commands.*;
import us.twoguys.thedarkness.player.PlayerDataMaster;
import us.twoguys.thedarkness.player.PlayerDataPersister;
import us.twoguys.thedarkness.schematics.*;
import us.twoguys.thedarkness.visualization.*;
import us.twoguys.thedarkness.mechanics.*;
import us.twoguys.thedarkness.mechanics.effects.Time;
import us.twoguys.thedarkness.mechanics.effects.TimeMaster;
import us.twoguys.thedarkness.mechanics.effects.TorchConsume;
import us.twoguys.thedarkness.mechanics.mobs.MobMaster;
import us.twoguys.thedarkness.mechanics.mobs.MobTargetListener;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger logger = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	public BeaconMaster beaconMaster = new BeaconMaster(this);
	private BeaconPersister beaconPersister = new BeaconPersister(this);
	public PlayerDataMaster beaconPlayerDataMaster = new PlayerDataMaster(this);
	private PlayerDataPersister beaconPlayerDataPersister = new PlayerDataPersister(this);
	public VisualizerCore visualizerCore = new VisualizerCore(this);
	public SchematicHandler schematicHandler = new SchematicHandler(this);
	public LocationCheckScheduler locCheck = new LocationCheckScheduler(this);
	public BeaconListenerMaster beaconListenerMaster = new BeaconListenerMaster(this);
	public LocTools locTools = new LocTools(this);
	public TimeMaster timeMaster = new TimeMaster(this);
	public MobMaster mobMaster = new MobMaster(this);
	public CommandMaster comandMaster = new CommandMaster();
	
	//Shield
	public Shield shield = null;
	public ShieldAPI shieldAPI = null;
	
	public void onEnable(){
		
		schematicHandler.loadAllSchematics();
		config.loadConfiguration();
		
		beaconPersister.load();
		beaconPlayerDataPersister.load();
		
		loadCommandExecutors();
		loadListeners();
		loadShield();

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
		
		TorchConsume torchConsume = new TorchConsume(this);
		pm.registerEvents(torchConsume, this);
		
		MobTargetListener mobTarget = new MobTargetListener(this);
		pm.registerEvents(mobTarget, this);
		
		//MobSpawnListener mobListener = new MobSpawnListener(this);
		//pm.registerEvents(mobListener, this);
		
		Message message = new Message(this);
		pm.registerEvents(message, this);
		
		Time time = new Time(this);
		pm.registerEvents(time, this);
		
		log("Listeners Loaded");
	}
	
	public void loadCommandExecutors(){
		
		CommandHandler commandMaster = new CommandHandler(this);
		this.getCommand("theDarkness").setExecutor(commandMaster);
		
	}
	
	public void loadShield(){
		Plugin x = getServer().getPluginManager().getPlugin("Shield");
		if (x != null && x instanceof Shield){
			shield = (Shield) x;
			shieldAPI = shield.getAPI();
			log(String.format("Hooked Shield %s", shield.getDescription().getVersion()));
		}else{
			log("Shield was not found.");
		}
	}
	
}