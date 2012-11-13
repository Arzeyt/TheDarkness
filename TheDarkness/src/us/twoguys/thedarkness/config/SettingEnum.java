package us.twoguys.thedarkness.config;

import java.util.Arrays;
import java.util.List;

public enum SettingEnum {
 
DURATION("duration","d");

 
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
  for (SettingEnum mob : SettingEnum.values()) {
         if (mob.getAliases().contains(input.toLowerCase())) {
             return mob;
         }
     }
     return null;
 }
}

