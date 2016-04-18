package control;

import java.util.ArrayList;
import java.util.Scanner;

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
		
		//Getting the map path from the user
		mapName = getMap();
		
		//Initializing the map
		m = new Map(this);
		
		//Loading in a map
		m.loadMap(mapName);
		
		//Loading the images
		background = loadImage(m.getBackground());
		building = loadImage(m.getBuilding());
		
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
		
		//Telling image coordinates to be at the center of the images
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
		popMatrix();
		
	}
	
	//Function to get the map name
	public String getMap() {
		//String to hold the map name
		String map;
		//Scanner to get input from the console
		Scanner input = new Scanner(System.in);
		
		//Prompting for map name in console
		System.out.println("Please enter the name of the map you would like to load: ");
		//Getting the map name from the console
		map = input.nextLine();
		//Returning the map name
		return map;
	}
	
	//function for listening to key strokes
	public void keyListener() {
		//Checking if a key is pressed
		if(keyPressed) {
			//Checking for special key
			if(key == CODED) {
				if(keyCode ==) {
					
				}
			}
		}
	}
	
	//Updating function
	public void update() {
		
	}
	
	//Rendering function
	public void render() {
		
	}
}
