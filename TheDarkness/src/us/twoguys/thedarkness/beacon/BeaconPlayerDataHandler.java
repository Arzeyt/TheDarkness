package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconPlayerDataHandler {

	TheDarkness plugin;
	
	HashSet<BeaconPlayerData> beaconPlayerData = new HashSet<BeaconPlayerData>();

	public BeaconPlayerDataHandler(TheDarkness instance){
		plugin=instance;
	}
	
	public void addBeaconPlayerData(BeaconPlayerData data){
		HashSet<BeaconPlayerData> temp = beaconPlayerData;
		temp.add(data);
		
		beaconPlayerData = temp;
	}
	
	public HashSet<BeaconPlayerData> getBeaconPlayerDataSet(){
		return beaconPlayerData;
	}
	
	public BeaconPlayerData getData(Player player){
		BeaconPlayerData playerData = null;
		if(playerDataExists(player)){
			for(BeaconPlayerData data : getBeaconPlayerDataSet()){
				if(data.getPlayerName() == player.getName()){
					playerData = data;
				}
			}
		}else{
			BeaconPlayerData newData = new BeaconPlayerData(player.getName(), 0, 0);
			plugin.log("BeaconData for "+player.getName()+" doesn't exist! Creating new save");
			addBeaconPlayerData(newData);
			playerData = newData;
		}
		return playerData;
	}
	
	public boolean playerDataExists(Player player){
		for(BeaconPlayerData data : getBeaconPlayerDataSet()){
			if(player.getName() == data.getPlayerName()){
				return true;
			}
		}
		return false;
	}
}
