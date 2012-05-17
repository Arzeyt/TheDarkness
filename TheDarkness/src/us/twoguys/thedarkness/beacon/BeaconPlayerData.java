package us.twoguys.thedarkness.beacon;

import java.io.Serializable;

public class BeaconPlayerData implements Serializable{

	private static final long serialVersionUID = 6969900904950793821L;

	private String playerName;
	private int beaconPoints, beaconAmount;
	
	public BeaconPlayerData(String playerName, int beaconPoints, int beaconAmount){
		this.playerName = playerName;
		this.beaconPoints = beaconPoints;
		this.beaconAmount = beaconAmount;
	}
	
	public void setPoints(int points){
		this.beaconPoints = points;
	}
	
	public void setBeaconAmount(int beaconAmount){
		this.beaconAmount = beaconAmount;
	}
	
	public void addPoints(int points){
		this.beaconPoints = this.beaconPoints + points;
	}
	
	public void addBeaconAmount(int beaconAmount){
		this.beaconAmount = this.beaconAmount + beaconAmount;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public int getBeaconPoints(){
		return this.beaconPoints;
	}
	
	public int getBeaconAmount(){
		return this.beaconAmount;
	}
	
}
