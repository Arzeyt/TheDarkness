package us.twoguys.thedarkness.mechanics.mirages;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.schematics.InvalidSchematicException;
import us.twoguys.thedarkness.schematics.SchematicObject;

/*
 * Mandatory
 * 		setting[0]= % chance
 * 		setting[1]= max distance
 * 		setting[2]= max mirages
 * 		setting[3]= 0 or 1 (0 to paste schematic with air, 1 to paste without air)
 * Optional
 * 		setting[4]= frequency
 */
public class CustomMirage extends Mirage{

	SchematicObject mirage;
	boolean active, air;
	int distance, maxMirages;
	
	public CustomMirage(TheDarkness instance, Player player, int level, String mirage) {
		super(instance, player, level);
		this.setting = plugin.config.getCustomMirageSettings(mirage, level);
		this.mirage = plugin.schematicHandler.getSchematicObject(mirage);
		this.air = setting.get(3) == 0 ? true : false;
		this.distance = setting.get(1);
		this.maxMirages = setting.get(2);
		createMirage();
	}
	
	public void createMirage(){
		
		taskId = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(!continueCheck(player)){
					plugin.visualizerCore.revertChunks(player);
					cancelTask();
				}
				if(passPercentChance(setting.get(0)) && maxMirages!= 0 ){
					if(air==true){
						Location loc = plugin.locTools.getRandomGround(player.getLocation(), distance);
						try {
							plugin.schematicHandler.paste(player, loc, mirage.getRawName());
						} catch (InvalidSchematicException e) {
							plugin.debug("could not paste "+mirage.getName());
							e.printStackTrace();
						}
						
					}else if(air==false){
						Location loc = plugin.locTools.getRandomGround(player.getLocation(), distance);
						try {
							plugin.schematicHandler.pasteNoAir(player, loc, mirage.getRawName());
						} catch (InvalidSchematicException e) {
							plugin.debug("could not paste "+mirage.getName());
							e.printStackTrace();
						}
					}
					maxMirages--;
					plugin.debug("custom mirage visualized. "+maxMirages+" mirages left.");
				}
				
			}
			
			
		}, 0L, getFrequency(4));
	}

	
}
