package us.twoguys.thedarkness.config;

import java.util.Arrays;
import java.util.List;

public enum SettingEnum {
 
DURATION("duration","d"),
CHANCE("chance","c"),
MOBSPAWNCHANCE("mobspawnchance","msc"),
MOBDESPAWNONTARGETCHANCE("mobdespawnontargetchance","despawnontarget","mobdespawn","mobdespawnchace"),
DISTANCE("range","distance","rng","r","dist"),
FREQUENCY("frequency","freq","f"),
AMOUNT("amount","amnt","amt","a"),

;

 
 private List<String> aliases;
 
 private SettingEnum(String...aliases) {
         this.aliases = Arrays.asList(aliases);
     }
 
 public String getPreferredName(){
  return aliases.get(0);
 }
 
 public List<String> getAliases(){
  return aliases;
 }
 
 public static SettingEnum find(String input){
  for (SettingEnum setting : SettingEnum.values()) {
         if (setting.getAliases().contains(input.toLowerCase())) {
             return setting;
         }
     }
     return null;
 }
}

