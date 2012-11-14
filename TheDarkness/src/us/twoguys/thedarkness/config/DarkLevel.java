package us.twoguys.thedarkness.config;

import java.util.HashMap;

public class DarkLevel {
	
	private int level;
	private int defaultCheckFrequency;
	private int distance;
	/**
	 * Contains the Name of the effect, and the settings of the effect
	 */
	private HashMap<String, String> messages = new HashMap<String, String>();
	private HashMap<String, ConfigSetting> effects = new HashMap<String, ConfigSetting>();
	private HashMap<String, ConfigSetting> mobs = new HashMap<String, ConfigSetting>();
	private HashMap<String, ConfigSetting> mirages = new HashMap<String, ConfigSetting>();
	
	public DarkLevel(int level, int defaultCheckFrequency, int distance, HashMap<String, String> messages, 
			HashMap<String, ConfigSetting> effects, HashMap<String, ConfigSetting> mobSpawns, 
			HashMap<String, ConfigSetting> mirages){
		this.level = level;
		this.defaultCheckFrequency = defaultCheckFrequency;
		this.distance = distance;
		this.messages = messages;
		this.effects = effects;
		this.mobs = mobSpawns;
		this.mirages = mirages;
		
	}

	public int getDefaultCheckFrequency(){
		return defaultCheckFrequency;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public HashMap<String, ConfigSetting> getEffects(){
		return effects;
	}
	
	public HashMap<String, ConfigSetting> getMobs(){
		return mobs;
	}
	
	public HashMap<String, ConfigSetting> getMirages(){
		return mirages;
	}
	
	public boolean hasEffects(){
		if(effects.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean hasMobs(){
		if(mobs.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean hasMirages(){
		if(mobs.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
}
