package control;

import java.util.ArrayList;

import map.*;
import map.Path.PathPoint;
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
	
	//Offset for the camera
	PVector off;
	//Map object
	Map m;
	//Camera object
	Camera cam;
	//String to hold path to map file
	String mapName;
	//Array List holding paths in the map
	ArrayList<Path> mapPaths;
	//Image objects for the background and building
	PImage background,building;
	
	//Called once
	public void setup() {
		//Setting the frame rate
		frameRate(60);
		
		background = loadImage("resources\\images\\backgrounds\\0.png");
		building = loadImage("resources\\images\\backgrounds\\1.png");
		
		//Setting the path for the map file
		mapName = "resources\\maps\\map1.txt";
		
		//Initializing the camera
		cam = new Camera(this);
		
		//Initializing the map
		m = new Map(this);
		
		//Loading in a map
		m.loadMap(mapName);
		//Getting the loaded in paths from the map class
		mapPaths = m.getPaths();
		
		//Initializing the offset vector
		off = new PVector(0,0);
		
		imageMode(CENTER);
	}
	
	//Called once a loop
	public void draw() {
		image(background,width/2,height/2);
		
		cam.update(off,1);
		off = cam.getOffSet();
		
		Path p = mapPaths.get(0);
		
		pushMatrix();
		translate(-off.x,-off.y);
		image(building,width/2,height-(building.height/2));
		p.display();
		popMatrix();
		
	}
}
