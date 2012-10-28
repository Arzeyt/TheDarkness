package us.twoguys.thedarkness.player;

import java.io.Serializable;

import us.twoguys.thedarkness.beacon.InsufficientPointsException;

public class PlayerData implements Serializable{

	private static final long serialVersionUID = 6969900904950793821L;

	private String playerName;
	private int darkEssence;
	
	public PlayerData(String playerName){
		this.playerName = playerName;
	}
	public PlayerData(String playerName, int beaconPoints, int beaconAmount){
		this.playerName = playerName;
		this.darkEssence = beaconPoints;
	}
	
	public void setPoints(int points){
		this.darkEssence = points;
	}
	
	public void incrementPoints(int points) throws InsufficientPointsException{
		this.darkEssence = this.darkEssence + points;
		if(darkEssence<0){
			darkEssence=darkEssence+points;
			throw new InsufficientPointsException(playerName+" does not have enough points");
		}
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public int getBeaconPoints(){
		return this.darkEssence;
	}
	
}
