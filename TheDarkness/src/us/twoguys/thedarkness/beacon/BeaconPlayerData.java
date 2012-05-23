package us.twoguys.thedarkness.beacon;

import java.io.Serializable;

public class BeaconPlayerData implements Serializable{

	private static final long serialVersionUID = 6969900904950793821L;

	private String playerName;
	private int beaconPoints;
	
	public BeaconPlayerData(String playerName, int beaconPoints, int beaconAmount){
		this.playerName = playerName;
		this.beaconPoints = beaconPoints;
	}
	
	public void setPoints(int points){
		this.beaconPoints = points;
	}
	
	public void incrementPoints(int points){
		this.beaconPoints = this.beaconPoints + points;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public int getBeaconPoints(){
		return this.beaconPoints;
	}
	
}
