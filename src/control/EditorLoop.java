package control;

import map.*;
import processing.core.*;

public class EditorLoop extends PApplet{
	
	//Nothing should change here
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new EditorLoop());
	}
	
	//Settings go here
	public void settings() {
		fullScreen();
	}
	
	
	PVector off;
	Map m;
	Camera cam;
	String mapName;
	
	//Called once
	public void setup() {
		//Setting the frame rate
		frameRate(60);
		
		//Setting the path for the map file
		mapName = "Resources\\Maps\\map1.txt";
		
		//Initializing the camera
		cam = new Camera(this);
		
		//Initializing the map
		m = new Map(this);
		
		//Loading in a map
		m.loadMap(mapName);
		
		//Initializing the offset vector
		off = new PVector(0,0);
	}
	
	//Called once a loop
	public void draw() {
		background(0);
		
		cam.update(off,1);
		off = cam.getOffSet();
		
		pushMatrix();
		translate(-off.x,-off.y);
		m.render(true);
		popMatrix();
		
	}
}
