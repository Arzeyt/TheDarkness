package us.twoguys.thedarkness.config;

import java.util.Arrays;
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
	HashMap<String, String> settings = new HashMap<String, String>();
	
	public ConfigSetting(String rawString){
		this.rawString = rawString;
		splitRawString();
		
	}
	
	public void splitRawString(){ //I'll list some examples to help 
			System.out.println("rawString is "+rawString);
			
		String[] split1 = rawString.split(": "); //tochconsume: blah:2 poop:20
			System.out.println("split1 = "+ Arrays.asList(split1));
			
		this.settingName = split1[0]; //torchconsume
			System.out.println("settingName = "+settingName);
			
		this.settingsStringRaw = split1[1]; //blah:2 poop:20
			System.out.println("settingsStringRaw = "+settingsStringRaw);
			
		this.settingsArrayRaw = this.settingsStringRaw.split(" "); //blah:2, poop:20
			System.out.println("settingsArrayRaw = "+Arrays.asList(settingsArrayRaw));
			
		HashMap<String, String> effectSettings = new HashMap<String, String>();
		for(String setting : settingsArrayRaw){
			String[] parts = setting.split(":");
			
			effectSettings.put(parts[0], parts[1]);
		}
		
		this.settings = effectSettings;
	}
	
	public String getSettingName(){
		return settingName;
	}
	
	public HashMap<String, String> getSettingsMap(){
		return settings;
	}
	
}
