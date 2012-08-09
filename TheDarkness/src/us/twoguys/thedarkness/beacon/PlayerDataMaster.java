package us.twoguys.thedarkness.beacon;

import java.util.HashSet;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;

public class PlayerDataMaster {

	TheDarkness plugin;
	
	private HashSet<PlayerData> beaconPlayerData = new HashSet<PlayerData>();

	public PlayerDataMaster(TheDarkness instance){
		plugin=instance;
	}
	
	/**
	 * 
	 * @param data
	 * @return True if the data was successfully added. False if the data already exists for that player.
	 */
	public boolean addBeaconPlayerData(PlayerData data){
		for(PlayerData cData : getBeaconPlayerDataSet()){
			if(data.getPlayerName().equals(cData.getPlayerName())){
				return false;
			}
		}
		HashSet<PlayerData> temp = beaconPlayerData;
		temp.add(data);
		
		beaconPlayerData = temp;
		
		return true;
	}
	
	public HashSet<PlayerData> getBeaconPlayerDataSet(){
		return beaconPlayerData;
	}
	
	/**
	 * 
	 * @param player
	 * If the player data does not exist, this method will instantiate a new data object. 
	 */
	public PlayerData getData(Player player){
		for(PlayerData data : getBeaconPlayerDataSet()){
			if(data.getPlayerName().equals(player.getName())) return data;
		}
		PlayerData newData = new PlayerData(player.getName(), 0, 0);
		if(addBeaconPlayerData(newData)){
			return newData;}
		return null;
		
	}
		
	public boolean playerDataExists(Player player){
		for(PlayerData data : getBeaconPlayerDataSet()){
			if(player.getName() == data.getPlayerName()){
				return true;
			}
		}
		return false;
	}
	
	public boolean subtractPoints(Player player, int amount){
		PlayerData bpd = getData(player);
		amount = Math.abs(amount);
		if(bpd.getBeaconPoints() < plugin.config.getBeaconCost()){
			return false;
		}else{
			try {
				bpd.incrementPoints(-1*amount);
			} catch (InsufficientPointsException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	public void addPoints(Player player, int amount){
		amount=Math.abs(amount);
		try {
			getData(player).incrementPoints(amount);
		} catch (InsufficientPointsException e) {
			e.printStackTrace();
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
