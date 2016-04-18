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
	Camera camera;
	//String to hold path to map file
	String mapName;
	//Array List holding paths in the map
	ArrayList<Path> mapPaths;
	//Image objects for the background and building
	//Image variables to hold background and building
	PImage background,building;
	
	//Called once
	public void setup() {
		//Setting the frame rate
		frameRate(60);
		
		background = loadImage("resources\\images\\backgrounds\\0.png");
		building = loadImage("resources\\images\\backgrounds\\1.png");
		
		//Setting the path for the map file
		mapName = "resources\\maps\\map1.txt";
		
		//Initializing the map
		m = new Map(this);
		
		//Loading in a map
		m.loadMap(mapName);
		//Getting the loaded in paths from the map class
		mapPaths = m.getPaths();
		
		//Camera variables 
		camera = new Camera(this);
		camera.setPushBorder(50);
		camera.setBoundX(true);
		camera.setBoundY(true);
		camera.setBoundXDim(-building.width/8,building.width/8);
		camera.setBoundYDim(-((building.height/2)-(this.height/2)),building.height/4);
		off = new PVector(0,0);
		
		imageMode(CENTER);
	}
	
	//Called once a loop
	public void draw() {
		image(background,width/2,height/2);
		
		camera.setCurOffSet(off);
		camera.calcOffSet();
		off = camera.getOffSet();
		
		Path p = mapPaths.get(0);
		
		pushMatrix();
		translate(-off.x,-off.y);
		image(building,width/2,height-(building.height/2));
		p.display();
		popMatrix();
		
	}
}
