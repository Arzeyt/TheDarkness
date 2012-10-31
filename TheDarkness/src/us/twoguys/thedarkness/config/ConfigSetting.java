package us.twoguys.thedarkness.config;

import java.util.HashMap;
/**
 * 
 * @author Nick
 * Holds strings and string arrays of a settings line in the config file.
 */
public class ConfigSetting {

	String 
		rawString, 
		settingName, 
		settingsStringRaw;
	String[] settingsArrayRaw;
	HashMap<String, Integer> settings = new HashMap<String, Integer>();
	
	public ConfigSetting(String rawString){
		this.rawString = rawString;
		splitRawString();
		
	}
	
	public void splitRawString(){ //I'll list some examples to help 
		String[] split1 = rawString.split(": "); //tochconsume: blah:2 poop:20
		this.settingName = split1[0]; //torchconsume
		this.settingsStringRaw = split1[1]; //blah:2 poop:20
		this.settingsArrayRaw = this.settingsStringRaw.split(" "); //blah:2, poop:20
		
		HashMap<String, Integer> effectSettings = new HashMap<String, Integer>();
		for(String setting : settingsArrayRaw){
			String[] parts = setting.split(":");
			
			effectSettings.put(parts[0], Integer.getInteger(parts[1]));
		}
		
		this.settings = effectSettings;
	}
	
	public String getSettingName(){
		return settingName;
	}
	
	public HashMap<String, Integer> getSettingsMap(){
		return settings;
	}
	
}
