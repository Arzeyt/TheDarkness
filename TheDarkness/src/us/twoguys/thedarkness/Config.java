package us.twoguys.thedarkness;

public class Config {

	TheDarkness plugin;
	
	public Config(TheDarkness instance){
		plugin = instance;
	}
	
	public void loadConfiguration(){
		
		plugin.getConfig().addDefault("Levels.1.message", true);
	}
}
