package us.twoguys.thedarkness.beacon;

public class InsufficientPointsException extends Exception{
	
	public InsufficientPointsException(String msg){
		super(msg);
	}
	
	public InsufficientPointsException(){
		super();
	}

}
