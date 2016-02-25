package gameObject;

import java.util.ArrayList;
import processing.core.*;

//Anything that is displayed but not interacted with
public class Tile extends GameObject{
	
	//Holds all of the frames of the tile
	int tileNum;
	int tileVal;
	
	public Tile(PApplet p, int tileNum, int tileVal, int x, int y) {
		super(p,x,y);
		this.tileNum = tileNum;
		this.tileVal = tileVal;
	}
	
	public void update() {
		
	}
	
	public void render() {
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		p.rotate(0);
		p.rectMode(PApplet.CENTER);
		p.fill(255);
		p.rect(0,0,20,20);
		p.fill(0);
		p.rect(0,0,10,10);
		p.popMatrix();
	}
}
