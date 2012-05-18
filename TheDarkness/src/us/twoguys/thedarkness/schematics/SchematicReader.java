package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

public class SchematicReader {

	private static Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
        Tag tag = items.get(key);
        return tag;
	}
	
    public void loadSchematic(){
    	 try {
             File f = new File("Small Temple.schematic");
             FileInputStream fis = new FileInputStream(f);
             NBTInputStream nbt = new NBTInputStream(fis);
             CompoundTag backuptag = (CompoundTag) nbt.readTag();
             Map<String, Tag> tagCollection = backuptag.getValue();

             short width = (Short)getChildTag(tagCollection, "Width", ShortTag.class).getValue();
             short height = (Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue();
             short length = (Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue();

             byte[] blocks = (byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue();
             byte[] data = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

             List entities = (List) getChildTag(tagCollection, "Entities", ListTag.class).getValue();
             List tileentities = (List) getChildTag(tagCollection, "TileEntities", ListTag.class).getValue();
             nbt.close();
             fis.close();
             System.out.println(entities);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
	
}
