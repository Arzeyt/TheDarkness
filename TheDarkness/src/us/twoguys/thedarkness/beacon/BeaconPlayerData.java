package us.twoguys.thedarkness.beacon;

import java.io.Serializable;

public class BeaconPlayerData implements Serializable{

	private static final long serialVersionUID = 6969900904950793821L;

	private String playerName;
	private int darkEssence;
	
	public BeaconPlayerData(String playerName, int beaconPoints, int beaconAmount){
		this.playerName = playerName;
		this.darkEssence = beaconPoints;
	}
	
	public void setPoints(int points){
		this.darkEssence = points;
	}
	
	public void incrementPoints(int points){
		this.darkEssence = this.darkEssence + points;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public int getBeaconPoints(){
		return this.darkEssence;
	}
	
}
