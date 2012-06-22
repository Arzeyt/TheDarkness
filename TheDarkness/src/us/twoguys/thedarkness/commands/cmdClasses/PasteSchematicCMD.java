package us.twoguys.thedarkness.commands.cmdClasses;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
import us.twoguys.thedarkness.schematics.InvalidSchematicException;

public class PasteSchematicCMD {

	TheDarkness plugin;
	
	public PasteSchematicCMD(TheDarkness instance){
		this.plugin=instance;
	}
	public boolean pasteSchematic(Player player, Location loc, String schematicName){
		try {
			plugin.schematicHandler.paste(player, loc, schematicName);
		} catch (InvalidSchematicException e) {
			plugin.debug(schematicName+" is not a valid schematic");
			e.printStackTrace();
		}
		return true;
	}
	
	public String toString(){
		return new String( "/theDarkess paste <schematic name>");
	}
}
