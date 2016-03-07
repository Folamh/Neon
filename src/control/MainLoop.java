
package control;

import processing.core.*;

import java.util.ArrayList;

import animation.*;

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
		//fullScreen();
		size(1000,700);
	}
	PImage building;
	Animation animation;
	Grid grid;
	PVector location = new PVector (4,4);
	PVector square = new PVector (4,4);
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		grid = new Grid(true,location,square);
		//grid.loadGrid();
		building = loadImage("Resources\\Images\\Backgrounds\\Building\\0.png");
		building.resize(width,height);
		
	}
	
	public void draw() {
		background(0);
		map.render();
		rect(10,10,10,10);
		
		image(building,0,0);
		noFill();
		stroke(0);
		rect(10,100,10,100);
		}

}
