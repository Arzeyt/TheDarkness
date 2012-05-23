package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class BeaconPlayerDataMaster {

	TheDarkness plugin;
	
	private HashSet<BeaconPlayerData> beaconPlayerData = new HashSet<BeaconPlayerData>();

	public BeaconPlayerDataMaster(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * 
	 * @param data
	 * @return True if the data was successfully added. False if the data already exists for that player.
	 */
	public boolean addBeaconPlayerData(BeaconPlayerData data){
		for(BeaconPlayerData cData : getBeaconPlayerDataSet()){
			if(data.getPlayerName().equals(cData.getPlayerName())){
				return false;
			}
		}
		
		HashSet<BeaconPlayerData> temp = beaconPlayerData;
		temp.add(data);
		
		beaconPlayerData = temp;
		
		return true;
	}
	
	public HashSet<BeaconPlayerData> getBeaconPlayerDataSet(){
		return beaconPlayerData;
	}
	
	/**
	 * 
	 * @param player
	 * If the player data does not exist, this method will instantiate a new data object. 
	 */
	public BeaconPlayerData getData(Player player){
		for(BeaconPlayerData data : getBeaconPlayerDataSet()){
			if(data.getPlayerName().equals(player.getName())) return data;
		}
		BeaconPlayerData newData = new BeaconPlayerData(player.getName(), 0, 0);
		if(addBeaconPlayerData(newData)){
			return newData;}
		return null;
		
	}
		
	public boolean playerDataExists(Player player){
		for(BeaconPlayerData data : getBeaconPlayerDataSet()){
			if(player.getName() == data.getPlayerName()){
				return true;
			}
		}
		return false;
	}
	
	public boolean subtractPoints(Player player, int amount){
		BeaconPlayerData bpd = getData(player);
		if(bpd.getBeaconPoints() < plugin.config.getBeaconCost()){
			return false;
		}else{
			bpd.incrementPoints(-1*amount);
			return true;
		}
	}
	
	public boolean canCreateBeacon(Player player, boolean sendErrorReason){
		String error;
		if(getData(player).getBeaconPoints() < plugin.config.getBeaconCost()){
			error = "Not enough points";
			return false;
		}else{
		
			return true;
		}
	}
}
