package us.twoguys.thedarkness.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
	/**
	 * contains each setting associate with its respective modifier
	 * @Example 
	 * 		duration, 3 
	 */
	HashMap<String, String> settings = new HashMap<String, String>(); 
	
	public ConfigSetting(String rawString){
		this.rawString = rawString;
		splitRawString();
		
	}
	
	public void splitRawString(){ //I'll list some examples to help 
			debug("rawString is "+rawString);
			if(rawString.length() < 3){
				warning("Delete config entries that do not contain " +
						"settings. If any config value is empty for a particular level, this will cause errors. Example:" +
						" If on level 2, you had the 'Effects' entry, but no effects, remove the 'Effects' line ");
				return;
			}
			
		String[] split1 = rawString.split(": "); //tochconsume: blah:2 poop:20 test:3
			debug("split1 = "+ Arrays.asList(split1));
			
		this.settingName = split1[0]; //torchconsume
			debug("settingName = "+settingName);
			
		this.settingsStringRaw = split1[1]; //blah:2 poop:20 test:3
			debug("settingsStringRaw = "+settingsStringRaw);
			
		this.settingsArrayRaw = this.settingsStringRaw.split(" "); //blah:2, poop:20, test:3
			debug("settingsArrayRaw = "+Arrays.asList(settingsArrayRaw));
			
		HashMap<String, String> settings = new HashMap<String, String>();
		
		for(String setting : settingsArrayRaw){
			String[] parts = setting.split(":"); //blah, 2 || poop, 20 || test, 3
			
			if(parts.length==1){
				warning("Invalid config value at \n"+rawString+" \nMake sure " +
						"appropriate naming conventions were used in this line! \nThis will likley break something");
				
				return;
			}
			
			settings.put(parts[0], parts[1]);
			
		}
		
		this.settings = settings;
	}
	
	public String getName(){
		return settingName;
	}
	
	public HashMap<String, String> getSettingsMap(){
		return settings;
	}
	
	public String getSetting(String setting){
		String correctedSetting;
		try{
			correctedSetting = SettingEnum.find(setting).getPreferredName();
		}catch(Exception e){
			correctedSetting = null;
		}
		return settings.get(correctedSetting);
		
	}
	
	public String getSetting(SettingEnum setting){
		return settings.get(setting.getPreferredName());
	}
	
	public int getIntSetting(String setting){
		return Integer.parseInt(getSetting(setting));
		
	}
	
	public int getIntSetting(SettingEnum setting){
		return Integer.parseInt(settings.get(setting.getPreferredName()));
	}
	
	public boolean containsSetting(SettingEnum setting){
		for(String stringSetting: settings.keySet()){
			if(getSetting(setting).equalsIgnoreCase(getSetting(stringSetting))){
				return true;
			}
		}
		return false;
	}
	
	private void debug(String msg){
		System.out.println("[TheDarkness DEBUG] "+msg);
	}
	
	private void warning(String msg){
		System.out.println("[TheDarkness SEVERE] "+msg);
	}
}
