package us.twoguys.thedarkness.player;

import java.util.HashSet;

import us.twoguys.thedarkness.beacon.InsufficientPointsException;

public class DarkPlayer {

	String name;
	int points;
	int level;
	int levelOverride=-1;
	
	HashSet<Class<?>> effects = new HashSet<Class<?>>();
	HashSet<Class<?>> mobs = new HashSet<Class<?>>();
	HashSet<Class<?>> mirages = new HashSet<Class<?>>();

	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getlevelOverride(){
		return levelOverride;
	}
	
	public boolean hasLevelOverride(){
		if(levelOverride>-1){
			return true;
		}
		else return false;
	}
	
	public void incrementPoints(int points) throws InsufficientPointsException{
		this.points = this.points + points;
		if(points<0){
			points=points+points;
			throw new InsufficientPointsException(name+" does not have enough points");
		}
	}
}
