package us.twoguys.thedarkness.beacon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconPlayerDataPersister {
TheDarkness plugin;
	
	public BeaconPlayerDataPersister(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void save(){
		//create a new File
		File saveFile = new File("plugins"+File.separator+"TheDarkness"+File.separator+"BeaconPlayerData.dat");
		
		if (!saveFile.exists()){
			try {
				new File("plugins" + File.separator + "TheDarkness").mkdir();
				saveFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try{
			FileOutputStream fos = new FileOutputStream("plugins"+File.separator+"TheDarkness"+File.separator+"BeaconPlayerData.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			try{
				plugin.log("Items to save: "+plugin.beaconPlayerDataHandler.getBeaconPlayerDataSet().size());
				oos.writeInt(plugin.beaconPlayerDataHandler.getBeaconPlayerDataSet().size());
			}catch(Exception e){
				plugin.log("No PlayerData to save!");
			}
				for(BeaconPlayerData beaconData : plugin.beaconPlayerDataHandler.getBeaconPlayerDataSet()){
					oos.writeObject(beaconData);
					plugin.log(beaconData.getPlayerName()+ " BeaconData was saved");
				
				}
			oos.close();
			plugin.log("PlayerData Saved");
		}catch(Exception e){
			e.printStackTrace();
			plugin.log("PlayerData did not save!");
		}
		
				
	}
	
	public void load(){
		
		File saveFile = new File("plugins"+File.separator+"TheDarkness"+File.separator+"BeaconPlayerData.dat");
		
		if(saveFile.exists()){
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try{
				fis = new FileInputStream(saveFile);
				ois = new ObjectInputStream(fis);
				
				Integer recordCount = ois.readInt();
				plugin.log("Items to read: "+recordCount);
				
				
				for(int i = 0; i < recordCount; i ++){
					BeaconPlayerData beacon = (BeaconPlayerData)ois.readObject();
					plugin.beaconPlayerDataHandler.addBeaconPlayerData(beacon);
					plugin.log(beacon.getPlayerName()+" Beacon Data was loaded");
				}
				
				plugin.log("BeaconPlayerData loaded!");
					
			}catch(FileNotFoundException e){
				plugin.log("Could not locate data file... ");
				e.printStackTrace();
			}catch(IOException e){
				plugin.log("IOException while trying to read data file");
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				plugin.log("Could not find class to load");
				e.printStackTrace();
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

