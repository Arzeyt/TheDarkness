package us.twoguys.thedarkness.schematics;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SchematicReader2 {
	
	/*
	private void saveSchematic(Location loc1, Location loc2, String schematic, Player player) throws EmptyClipboardException, IOException, DataException {
	    WorldEditPlugin wep = (WorldEditPlugin) te.getServer().getPluginManager().getPlugin("WorldEdit");
	    WorldEdit we = wep.getWorldEdit();
	    BukkitPlayer localPlayer = wep.wrapPlayer(player);
	    LocalSession localSession = we.getSession(localPlayer);
	    EditSession editSession = localSession.createEditSession(localPlayer);
	 
	    double x1 = Math.min(loc1.getX(), loc2.getX());
	    double x2 = Math.max(loc1.getX(), loc2.getX());
	    double y1 = Math.min(loc1.getY(), loc2.getY());
	    double y2 = Math.max(loc1.getY(), loc2.getY());
	    double z1 = Math.min(loc1.getZ(), loc2.getZ());
	    double z2 = Math.max(loc1.getZ(), loc2.getZ());
	 
	    Location l1 = new Location(loc1.getWorld(), x1, y1, z1);
	    Location l2 = new Location(loc1.getWorld(), x2, y2, z2);
	    Vector min = new Vector(l1.getBlockX(), l1.getBlockY(), l1.getBlockZ());
	    Vector max = new Vector(l2.getBlockX(), l2.getBlockY(), l2.getBlockZ());
	 
	    File saveFile;
	    try {
	        saveFile = we.getSafeSaveFile(localPlayer, te.getDataFolder(), schematic, "schematic", new String[]{"schematic"});
	    } catch (FilenameException ex) {
	        te.logUtil.warning(ex.getMessage());
	        return;
	    }
	 
	    te.logUtil.debug(min + " - " + max);
	 
	    editSession.enableQueue();
	    CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
	    clipboard.copy(editSession);
	    clipboard.saveSchematic(saveFile);
	    editSession.flushQueue();
	}
	 
	private void loadSchematic(Location loc1, Location loc2, String schematic, Player player) throws DataException, IOException, EmptyClipboardException, MaxChangedBlocksException {
	    WorldEditPlugin wep = (WorldEditPlugin) te.getServer().getPluginManager().getPlugin("WorldEdit");
	    WorldEdit we = wep.getWorldEdit();
	    BukkitPlayer localPlayer = wep.wrapPlayer(player);
	    LocalSession localSession = we.getSession(localPlayer);
	    EditSession editSession = localSession.createEditSession(localPlayer);
	 
	    double x1 = Math.min(loc1.getX(), loc2.getX());
	    double x2 = Math.max(loc1.getX(), loc2.getX());
	    double y1 = Math.min(loc1.getY(), loc2.getY());
	    double y2 = Math.max(loc1.getY(), loc2.getY());
	    double z1 = Math.min(loc1.getZ(), loc2.getZ());
	    double z2 = Math.max(loc1.getZ(), loc2.getZ());
	 
	    Location l1 = new Location(loc1.getWorld(), x1, y1, z1);
	    Location l2 = new Location(loc1.getWorld(), x2, y2, z2);
	    Vector min = new Vector(l1.getBlockX(), l1.getBlockY(), l1.getBlockZ());
	    Vector max = new Vector(l2.getBlockX(), l2.getBlockY(), l2.getBlockZ());
	 
	    File saveFile;
	    try {
	        saveFile = we.getSafeOpenFile(localPlayer, te.getDataFolder(), schematic, "schematic", new String[]{"schematic"});
	    } catch (FilenameException ex) {
	        te.logUtil.warning(ex.getMessage());
	        return;
	    }
	 
	    te.logUtil.debug(min + " - " + max);
	 
	    editSession.enableQueue();
	    localSession.setClipboard(CuboidClipboard.loadSchematic(saveFile));
	    com.sk89q.worldedit.Vector pos = localSession.getClipboard().getOrigin();
	    localSession.getClipboard().place(editSession, pos, false);
	    editSession.flushQueue();
	    we.flushBlockBag(localPlayer, editSession);
	}
	*/
}
