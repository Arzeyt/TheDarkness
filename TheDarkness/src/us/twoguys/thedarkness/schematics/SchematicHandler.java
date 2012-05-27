package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.minecraft.server.TileEntity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


import us.twoguys.lib.jnbt.ByteArrayTag;
import us.twoguys.lib.jnbt.CompoundTag;
import us.twoguys.lib.jnbt.IntTag;
import us.twoguys.lib.jnbt.ListTag;
import us.twoguys.lib.jnbt.NBTInputStream;
import us.twoguys.lib.jnbt.NBTOutputStream;
import us.twoguys.lib.jnbt.ShortTag;
import us.twoguys.lib.jnbt.Tag;
import us.twoguys.thedarkness.TheDarkness;

public class SchematicHandler {

	TheDarkness plugin;
	HashSet<SchematicObject> schematics = new HashSet<SchematicObject>();
	
	public SchematicHandler(TheDarkness instance){
		this.plugin = instance;
	}
	private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
        Tag tag = items.get(key);
        return tag;
	}
	/**
	 * must be called before schematics can be used!
	 */
	public void loadAllSchematics(){
		try{
			File f = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Schematics");
			File[] schematics = f.listFiles();
			for(File schematic : schematics){
				loadSchematic(schematic.getName());
			}
		}catch(Exception e){
			plugin.logSevere("Could not load schematics");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadSchematic(String schematicName){
		SchematicObject so = new SchematicObject();
		
    	 try {
    		 File f = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Schematics"+File.separator+schematicName);
             FileInputStream fis = new FileInputStream(f);
             NBTInputStream nbt = new NBTInputStream(fis);
             CompoundTag backuptag = (CompoundTag) nbt.readTag();
             Map<String, Tag> tagCollection = backuptag.getValue();

             so.name = schematicName;
             
             so.width = (Short) getChildTag(tagCollection, "Width", ShortTag.class).getValue();
             so.height = (Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue();
             so.length = (Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue();

             so.blocks = (byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue();
             so.data = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

             List<Entity> entities = (List<Entity>) getChildTag(tagCollection, "Entities", ListTag.class).getValue();
             List<TileEntity> tileEntities = (List<TileEntity>) getChildTag(tagCollection, "TileEntities", ListTag.class).getValue();
             
             nbt.close();
             fis.close();
             
             schematics.add(so);
             
             plugin.debug(so.width +" "+ so.height +" "+ so.length +" "+ so.blocks +" "+ so.data);
             plugin.debug("loaded "+schematicName);
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
	 
    public void paste(Player player, Location loc, String schematicName){
		SchematicObject s = getSchematicObject(schematicName);
    	int index = 0;
		
		for(int x = 0; x < s.width; x ++){
			for(int y = 0; y < s.height; y ++){
				for( int z = 0; z < s.length; z ++){
					Location vloc = new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y, loc.getZ()+z);
					plugin.visualizerCore.visualizeBlock(player, vloc, s.blocks[index], s.data[index]);
					index ++;
				}
			}
		}
    }
    
    public HashSet<SchematicObject> getSchematics(){
    	return schematics;
    }
    
    public SchematicObject getSchematicObject(String schematicName){
    	for(SchematicObject schematic : getSchematics()){
    		if(schematic.name.equals(schematicName)); return schematic;
    	}
    	plugin.debug("getSchematicObjet returned null");
		return null;
    }
    
}