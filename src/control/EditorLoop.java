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
		//fullScreen();
		//Setting resolution to 720p
		size(1280,720, JAVA2D);
	}
	
	//Offset for the camera
	PVector off;
	//Camera object
	Camera camera;
	
	//Map object
	Map m;
	//String to hold path to map file
	String mapName;
	//Array List holding paths in the map
	ArrayList<Path> mapPaths;
	//Image variables to hold background and building
	PImage background,building;
	
	//Current thing being edited
	int curEdit;
	
	//bool flag for checking if key was pressed
	boolean pressed;
	
	//Called once
	public void setup() {
		//Setting the desired frame rate
		frameRate(60);
		
		//Setting default thing to be editing
		curEdit = 0;
		
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
		
		//Setting pressed flag to false
		pressed = false;
		
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
		update();
		render();
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
		map = "resources/maps/" + input.nextLine();
		//Returning the map name
		return map;
	}
	
	//function for listening to key strokes
	public void keyListener() {
		//Checking if a key is pressed
		if(keyPressed) {
			//Checking if key was pressed last frame
			if(!pressed) {
				//Setting key pressed to true
				pressed = true;
				
				//Checking for special key
				if(key == CODED) {
					//Checking if the left arrow key is pressed
					if(keyCode == LEFT) {
						if(curEdit == 0) {
							m.setTowerGridCol(m.getTowerGridCol()-1);
						}else if(curEdit == 1) {
							m.setPathGridCol(m.getPathGridCol()-1);
						}
					}
					
					//Checking if the right arrow key is pressed
					if(keyCode == RIGHT) {
						if(curEdit == 0) {
							m.setTowerGridCol(m.getTowerGridCol()+1);
						}else if(curEdit == 1) {
							m.setPathGridCol(m.getPathGridCol()+1);
						}
					}
					
					//Checking of the up arrow key is pressed
					if(keyCode == UP) {
						if(curEdit == 0) {
							m.setTowerGridRow(m.getTowerGridRow()+1);
						}else if(curEdit == 1) {
							m.setPathGridRow(m.getPathGridRow()+1);
						}
					}
					
					//Checking if the down arrow key is pressed
					if(keyCode == DOWN) {
						if(curEdit == 0) {
							m.setTowerGridRow(m.getTowerGridRow()-1);
						}else if(curEdit == 1) {
							m.setPathGridRow(m.getPathGridRow()-1);
						}
					}
				}
				
		
			}
			
			if(key == 'w') {
				if(curEdit == 0) {
					m.setTowerGridGapY(m.getTowerGridGapY()-1);
				}else if(curEdit == 1) {
					m.setPathGridGapY(m.getPathGridGapY()-1);
				}
			}
			
			if(key == 'a') {
				if(curEdit == 0) {
					m.setTowerGridGapX(m.getTowerGridGapX()-1);
				}else if(curEdit == 1) {
					m.setPathGridGapX(m.getPathGridGapX()-1);
				}
			}
			
			if(key == 's') {
				if(curEdit == 0) {
					m.setTowerGridGapY(m.getTowerGridGapY()+1);
				}else if(curEdit == 1) {
					m.setPathGridGapY(m.getPathGridGapY()+1);
				}
			}
			
			if(key == 'd') {
				if(curEdit == 0) {
					m.setTowerGridGapX(m.getTowerGridGapX()+1);
				}else if(curEdit == 1) {
					m.setPathGridGapX(m.getPathGridGapX()+1);
				}
			}
			
			if(key == 'q') {
				if(curEdit == 0) {
					m.setTowerGridW(m.getTowerGridW()-1);
				}else if(curEdit == 1) {
					m.setPathGridW(m.getPathGridW()-1);
				}
			}
			
			if(key == 'e') {
				if(curEdit == 0) {
					m.setTowerGridW(m.getTowerGridW()+1);
				}else if(curEdit == 1) {
					m.setPathGridW(m.getPathGridW()+1);
				}
			}
			
			if(key == 'r') {
				if(curEdit == 0) {
					m.setTowerGridH(m.getTowerGridH()+1);
				}else if(curEdit == 1) {
					m.setPathGridH(m.getPathGridH()+1);
				}
			}
			
			if(key == 'f') {
				if(curEdit == 0) {
					m.setTowerGridH(m.getTowerGridH()-1);
				}else if(curEdit == 1) {
					m.setPathGridH(m.getPathGridH()-1);
				}
			}
			
			if(key == '1') {
				curEdit = 0;
			}
			
			if(key == '2') {
				curEdit = 1;
			}
		}
			
		
		if(!keyPressed) {
			pressed = false;
		}
	}
	
	//Updating function
	public void update() {
		keyListener();
		camera.setCurOffSet(off);
		camera.calcOffSet();
		off = camera.getOffSet();
		
		Path p = mapPaths.get(0);
		
	}
	
	//Rendering function
	public void render() {
		image(background,width/2,height/2);
		pushMatrix();
		translate(off.x,off.y);
		image(building,width/2,height/2);
		if(curEdit == 0) {
			m.drawTowerGrid();
		} else if(curEdit == 1) {
			m.drawPathGrid();
		}
		popMatrix();
	}
}
