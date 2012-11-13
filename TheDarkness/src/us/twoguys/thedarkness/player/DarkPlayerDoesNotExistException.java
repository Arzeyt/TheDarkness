package us.twoguys.thedarkness.player;

public class DarkPlayerDoesNotExistException extends Exception{

	public DarkPlayerDoesNotExistException(String msg){
		super(msg);
	}
	
	public DarkPlayerDoesNotExistException(){
		super();
	}
}
