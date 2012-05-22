package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import net.minecraft.server.TileEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

import us.twoguys.thedarkness.TheDarkness;

public class SchematicHandler {

	List<TileEntity> tileentities;
	TheDarkness plugin;
	
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

             short width = (Short) getChildTag(tagCollection, "Width", ShortTag.class).getValue();
             short height = (Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue();
             short length = (Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue();

             byte[] blocks = (byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue();
             byte[] data = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

             List entities = (List) getChildTag(tagCollection, "Entities", ListTag.class).getValue();
             List<TileEntity> tileentities = (List<TileEntity>) getChildTag(tagCollection, "TileEntities", ListTag.class).getValue();
             
             nbt.close();
             fis.close();
             System.out.println(entities);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
	
    public void paste(Location loc){
    	int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
    	for(TileEntity te : this.tileentities){
    		Block b = (Block)te.q;
    		loc.getBlock().setType(b.getType());
    		
    		
    		
    		
        }
    }
    
}
