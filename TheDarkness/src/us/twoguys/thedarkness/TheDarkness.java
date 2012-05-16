package us.twoguys.thedarkness;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class TheDarkness extends JavaPlugin{

	//logger
	private Logger log = Logger.getLogger("Minecraft");
	
	//Classes
	public Config config = new Config(this);
	//Listeners
	
	public void onEnable(){
		config.loadConfiguration();
	}

}
