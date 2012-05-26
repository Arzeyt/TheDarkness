package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
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
             System.out.println(tileEntities +" "+ width +" "+ height +" "+ length +" "+ blocks +" "+ data +" "+ entities);
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
    
    public void save(){
    }
    
    // Stolen from   https://github.com/Adamki11s/Regios/blob/master/src/couk/Adamki11s/Regios/RBF/RBF_Save.java
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
    Region region;
	String backupname;
	Player player;
	boolean isSharing;
	Location l1, l2;

	public synchronized void startSave(Region r, Location l11, Location l22, String bckn, Player p, boolean sh) {
		this.region = r;
		this.backupname = bckn;
		this.player = p;
		this.isSharing = sh;
		this.l1 = l11;
		this.l2 = l22;
		Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Regios.regios, new Runnable() {

			public void run() {
				if (!isSharing) {
					try {
						saveRegion(region, backupname, player);
					} catch (RegionExistanceException e) {
						e.printStackTrace();
					} catch (FileExistanceException e) {
						e.printStackTrace();
					}
				} else {
					saveBlueprint(l1, l2, backupname, player);
				}
			}

		}, 1L);
	}

	public synchronized void saveRegion(Region r, String backupname, Player p) throws RegionExistanceException, FileExistanceException {
		if (p != null) {
			p.sendMessage(ChatColor.GREEN + "[Regios] Creating .rbf backup file...");
		}
		if (p != null) {
			if (!super.canModifyBasic(r, p)) {
				p.sendMessage(ChatColor.RED + "[Regios] You are not permitted to modify this region!");
				return;
			}
		}

		if (r == null) {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] That Region does not exist!");
			}
			throw new RegionExistanceException("UNKNOWN");
		}

		File f = new File("plugins" + File.separator + "Regios" + File.separator + "Database" + File.separator + r.getName() + File.separator + "Backups" + File.separator
				+ backupname + ".rbf");

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] A backup with the name " + ChatColor.BLUE + backupname + ChatColor.RED + " already exists!");
			}
			throw new FileExistanceException("UNKNOWN", true);
		}

		World w = r.getL1().getWorld();
		Location max = new Location(w, Math.max(r.getL1().getX(), r.getL2().getX()), Math.max(r.getL1().getY(), r.getL2().getY()),
				Math.max(r.getL1().getZ(), r.getL2().getZ())), min = new Location(w, Math.min(r.getL1().getX(), r.getL2().getX()),
				Math.min(r.getL1().getY(), r.getL2().getY()), Math.min(r.getL1().getZ(), r.getL2().getZ()));

		int width = max.getBlockX() - min.getBlockX();
		int height = max.getBlockY() - min.getBlockY();
		int length = max.getBlockZ() - min.getBlockZ();

		width += 1;
		height += 1;
		length += 1;

		if (width > 65535) {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] The width is too large for a .rbf file!");

				p.sendMessage(ChatColor.RED + "[Regios] Max width : 65535. Your size : " + ChatColor.BLUE + width);
			}
			return;
		}
		if (height > 65535) {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] The height is too large for a .rbf file!");

				p.sendMessage(ChatColor.RED + "[Regios] Max height : 65535. Your size : " + ChatColor.BLUE + width);
			}
			return;
		}
		if (length > 65535) {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] The length is too large for a .rbf file!");

				p.sendMessage(ChatColor.RED + "[Regios] Max length : 65535. Your size : " + ChatColor.BLUE + width);
			}
			return;
		}

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
		if (p != null) {
			p.sendMessage(ChatColor.GREEN + "[Regios] Region " + ChatColor.BLUE + backupname + ChatColor.GREEN + " saved to .rbf file successfully!");
		}

		RegionBackupEvent event = new RegionBackupEvent("RegionBackupEvent");
		event.setProperties(r, backupname, p);
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public synchronized void saveBlueprint(Location l1, Location l2, String backupname, Player p) {
		try {
			if (p != null) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Creating .blp Blueprint file...");
			}
			File f = new File("plugins" + File.separator + "Regios" + File.separator + "Blueprints" + File.separator + backupname + ".blp");

			if (!f.exists()) {
				f.createNewFile();
			} else {
				if (p != null) {
					p.sendMessage(ChatColor.RED + "[Regios] A Blueprint file with the name " + ChatColor.BLUE + backupname + ChatColor.RED + " already exists!");
					return;
				}
			}

			World w = l1.getWorld();
			Location max = new Location(w, Math.max(l1.getX(), l2.getX()), Math.max(l1.getY(), l2.getY()), Math.max(l1.getZ(), l2.getZ())), min = new Location(w, Math.min(
					l1.getX(), l2.getX()), Math.min(l1.getY(), l2.getY()), Math.min(l1.getZ(), l2.getZ()));

			int width = max.getBlockX() - min.getBlockX();
			int height = max.getBlockY() - min.getBlockY();
			int length = max.getBlockZ() - min.getBlockZ();

			width += 1;
			height += 1;
			length += 1;

			if (width > 65535) {
				if (p != null) {
					p.sendMessage(ChatColor.RED + "[Regios] The width is too large for a .blp file!");

					p.sendMessage(ChatColor.RED + "[Regios] Max width : 65535. Your size : " + ChatColor.BLUE + width);
				}
				return;
			}
			if (height > 65535) {
				if (p != null) {
					p.sendMessage(ChatColor.RED + "[Regios] The height is too large for a .blp file!");

					p.sendMessage(ChatColor.RED + "[Regios] Max height : 65535. Your size : " + ChatColor.BLUE + width);
				}
				return;
			}
			if (length > 65535) {
				if (p != null) {
					p.sendMessage(ChatColor.RED + "[Regios] The length is too large for a .blp file!");

				}
				p.sendMessage(ChatColor.RED + "[Regios] Max length : 65535. Your size : " + ChatColor.BLUE + width);
				return;
			}

			HashMap<String, Tag> backuptag = new HashMap<String, Tag>();

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

			CompoundTag compoundTag = new CompoundTag("TRX", backuptag);

			NBTOutputStream nbt = new NBTOutputStream(new FileOutputStream(f));
			nbt.writeTag(compoundTag);
			nbt.close();
			if (p != null) {
				p.sendMessage(ChatColor.GREEN + "[Regios] Blueprint " + ChatColor.BLUE + backupname + ChatColor.GREEN + " saved to .blp file successfully!");
			}
		} catch (Exception ex) {
			if (p != null) {
				p.sendMessage(ChatColor.RED + "[Regios] Error saving region! Stack trace printed in console.");
			}
			ex.printStackTrace();
		}
	}

}
}
