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
		fullScreen();
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	
	//Only use this for initilising variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources\\Maps\\map1.txt");
		tiles = map.getTiles();
	}
	
	public void draw() {
		background(0);
		map.render();
		
	}

}
