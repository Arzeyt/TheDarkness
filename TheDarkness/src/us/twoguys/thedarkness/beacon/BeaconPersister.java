package us.twoguys.thedarkness.beacon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconPersister {

	TheDarkness plugin;
	
	public BeaconPersister(TheDarkness instance){
		this.plugin = instance;
	}
	
	public void Save() throws IOException{
		//create a new File
		File saveFile = new File("plugins"+File.separator+"TheDarkness"+File.separator+"Beacons.dat");
		
		saveFile.createNewFile();
		
		try{
			FileOutputStream fos = new FileOutputStream("plugins"+File.separator+"TheDarkness"+File.separator+"Beacons.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			try{
			
			}catch(Exception e){
				
			}
			
		}catch(Exception e){
			
		}
	}
	
}
