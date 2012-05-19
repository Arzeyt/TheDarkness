package us.twoguys.thedarkness.beacon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconPersister {

	TheDarkness plugin;
	
	public BeaconPersister(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void save(){
		//create a new File
		File saveFile = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Beacons.dat");

		if (!saveFile.exists()){
			try {
				new File("plugins" + File.separator + "TheDarkness").mkdir();
				saveFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try{
			FileOutputStream fos = new FileOutputStream("plugins"+File.separator+"TheDarkness"+File.separator+"Beacons.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			try{
				oos.writeInt(plugin.beaconHandler.getBeaconSet().size());
			}catch(Exception e){
				plugin.log("No Beacons to save!");
			}
				for(BeaconData beacon : plugin.beaconHandler.getBeaconSet()){
					oos.writeObject(beacon);
				
				}
			oos.close();
			plugin.log("Beacons Saved");
		}catch(Exception e){
			e.printStackTrace();
			plugin.log("Beacons did not save!");
		}
		
				
	}
	
	public void load(){
		
		File saveFile = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Beacons.dat");
		
		if(saveFile.exists()){
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try{
				fis = new FileInputStream(saveFile);
				ois = new ObjectInputStream(fis);
				
				Integer recordCount = ois.readInt();
				
				for(int i = 0; i < recordCount; i ++){
					BeaconData beacon = (BeaconData)ois.readObject();
					plugin.beaconHandler.addBeacon(beacon);
				}
				
				plugin.log("Beacons loaded!");
					
			}catch(FileNotFoundException e){
				plugin.log("Could not locate data file... ");
				e.printStackTrace();
			}catch(IOException e){
				plugin.log("IOException while trying to read data file");
			}catch(ClassNotFoundException e){
				plugin.log("Could not find class to load");
			}finally{
				try{
					ois.close();
				}catch(IOException e){
					plugin.log("Error while trying to close input stream");
				}
			}
		}
	}
}
	

