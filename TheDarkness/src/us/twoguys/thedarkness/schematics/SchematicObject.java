package us.twoguys.thedarkness.schematics;

public class SchematicObject {

	String name;
	
	short width, height, length;
	
	byte[] blocks, data;
	
	public String getName(){
		return name;
	}
	
	public String getRawName(){
		String[] string = name.split(".schematic");
		return string[0];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getLength(){
		return length;
	}
	
	public byte[] getBlocks(){
		return blocks;
	}
	
	public byte[] getData(){
		return data;
	}
}
