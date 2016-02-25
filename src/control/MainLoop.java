package control;

import processing.core.*;
import java.util.ArrayList;
import gameObject.*;
import map.*;

public class MainLoop extends PApplet{
	
	//DO NOT CHANGE THIS UNDER ANY CIRCUMSTANCES!!!!!!!!!
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new MainLoop());
	}
	
	//Settings go here
	public void settings() {
		size(1280,720);
	}
	
	Map map;
	Camera cam;
	PVector off;
	PVector set;
	ArrayList<Tile> tiles;
	
	//Only use this for initilising variables
	public void setup() {
		frameRate(60);
		map = new Map(this);
		off = new PVector(0,0);
		map.loadMap("Resources\\Maps\\map1.txt");
		tiles = map.getTiles();
		cam = new Camera(this);
	}
	
	public void draw() {
		background(0);
		
		cam.update(off);
		off = cam.getOffSet();
		
		pushMatrix();
		translate(-off.x,-off.y);
		map.render();
		popMatrix();
		
	}

}
