package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.TileEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


import us.twoguys.lib.jnbt.ByteArrayTag;
import us.twoguys.lib.jnbt.CompoundTag;
import us.twoguys.lib.jnbt.ListTag;
import us.twoguys.lib.jnbt.NBTInputStream;
import us.twoguys.lib.jnbt.ShortTag;
import us.twoguys.lib.jnbt.Tag;
import us.twoguys.thedarkness.TheDarkness;

public class SchematicHandler {

	TheDarkness plugin;
	
	short width;
	short height;
	short length;
	byte[] blocks;
	byte[] data;
	List<Entity> entities;
	List<TileEntity> tileEntities;
	
	public SchematicHandler(TheDarkness instance){
		this.plugin = instance;
	}
	private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
        Tag tag = items.get(key);
        return tag;
	}
	
	@SuppressWarnings("unchecked")
	public void loadSchematic(){
    	 try {
    		 File f = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Schematics"+File.separator+"testa.schematic");
             FileInputStream fis = new FileInputStream(f);
             NBTInputStream nbt = new NBTInputStream(fis);
             CompoundTag backuptag = (CompoundTag) nbt.readTag();
             Map<String, Tag> tagCollection = backuptag.getValue();

             width = (Short) getChildTag(tagCollection, "Width", ShortTag.class).getValue();
             height = (Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue();
             length = (Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue();

             blocks = (byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue();
             data = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

             entities = (List<Entity>) getChildTag(tagCollection, "Entities", ListTag.class).getValue();
             tileEntities = (List<TileEntity>) getChildTag(tagCollection, "TileEntities", ListTag.class).getValue();
             
             nbt.close();
             fis.close();
             System.out.println(tileEntities);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
	 
    public void paste(Player player, Location loc){
    	int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
    	for(TileEntity te : this.tileEntities){
    		World world = te.world.getWorld();
    		
    		int schX = te.x;
    		int schY = te.y;
    		int schZ = te.z;
    		
    		//Bukkit Material
    		Material schMaterial = Material.getMaterial(te.q.material.toString());
    		
    		//Bukkit block
    		Block schBlock = world.getBlockAt(x+schX, y+schY, z+schZ);
    		
    		plugin.visualizerCore.visualizeBlock(player, schBlock.getLocation(), schMaterial);
    		
    		
        }
    }
    
    /* Stolen from https://github.com/Adamki11s/Regios/blob/master/src/couk/Adamki11s/Regios/RBF/RBF_Save.java
    public void backup(){
		HashMap<String, Tag> backuptag = new HashMap<String, Tag>();
	
		// Copy
		byte[] blockID = new byte[width * height * length];
		byte[] blockData = new byte[width * height * length];
	
		int index = 0;
	
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					blockID[index] = (byte) w.getBlockAt(min.getBlockX() + x, min.getBlockY() + y, min.getBlockZ() + z).getTypeId();
					blockData[index] = (byte) w.getBlockAt(min.getBlockX() + x, min.getBlockY() + y, min.getBlockZ() + z).getData();
					index++;
				}
			}
		}
	
		backuptag.put("BlockID", new ByteArrayTag("BlockID", blockID));
		backuptag.put("Data", new ByteArrayTag("Data", blockData));
		backuptag.put("StartX", new IntTag("StartX", min.getBlockX()));
		backuptag.put("StartY", new IntTag("StartY", min.getBlockY()));
		backuptag.put("StartZ", new IntTag("StartZ", min.getBlockZ()));
		backuptag.put("XSize", new IntTag("XSize", width));
		backuptag.put("YSize", new IntTag("YSize", height));
		backuptag.put("ZSize", new IntTag("ZSize", length));
	
		CompoundTag compoundTag = new CompoundTag("RBF", backuptag);
		try {
			NBTOutputStream nbt = new NBTOutputStream(new FileOutputStream(f));
			nbt.writeTag(compoundTag);
			nbt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    */
}
