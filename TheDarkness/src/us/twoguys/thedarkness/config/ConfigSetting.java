package us.twoguys.thedarkness.config;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.ChatColor;
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
			if(rawString.length() < 3){
				System.out.println("[TheDarkness SEVERE] Severe error! Delete config entries that do not contain " +
						"settings. If any config value is empty for a particular level, this will cause errors. Example:" +
						" If on level 2, you had the 'Effects' entry, but no effects, remove the 'Effects' line ");
				return;
			}
			
		String[] split1 = rawString.split(": "); //tochconsume: blah:2 poop:20
			debug("split1 = "+ Arrays.asList(split1));
			
		this.settingName = split1[0]; //torchconsume
			debug("settingName = "+settingName);
			
		this.settingsStringRaw = split1[1]; //blah:2 poop:20
			debug("settingsStringRaw = "+settingsStringRaw);
			
		this.settingsArrayRaw = this.settingsStringRaw.split(" "); //blah:2, poop:20
			debug("settingsArrayRaw = "+Arrays.asList(settingsArrayRaw));
			
		HashMap<String, String> effectSettings = new HashMap<String, String>();
		for(String setting : settingsArrayRaw){
			String[] parts = setting.split(":");
			
			if(parts.length==1){
				System.out.println("[TheDarkness SEVERE] Severe error! Invalid config value at "+rawString+". Make sure " +
						"appropriate naming conventions were used in this line! This will likley break something");
				
				return;
			}
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
	
	private void debug(String msg){
		System.out.println("[TheDarkness DEBUG] "+msg);
	}
}
