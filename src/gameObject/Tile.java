package gameObject;

import java.util.ArrayList;
import processing.core.PImage;

//Anything that is displayed but not interacted with
public class Tile extends GameObject{
	
	//Holds all of the frames of the tile
	ArrayList<PImage> frames;
	int tileNum;
	int tileVal;
	
	Tile(int tileNum, int tileVal, int x, int y, ArrayList<PImage> frames) {
		super(x,y);
		this.frames = frames;
		this.tileNum = tileNum;
		this.tileVal = tileVal;
	}
	
	void update() {
		
	}
	
	void render() {
		
	}
}
