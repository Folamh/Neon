
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
		size(500,500);
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	ArrayList<PVector> path;
	BasicEnemy enemy;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		PVector e = new PVector(200, 200);
		path = new ArrayList<PVector>();
		path.add(e);
		enemy = new BasicEnemy(this, 100, 100, path);
	}
	
	public void draw() {
		background(0);
		map.render();
		enemy.render();
	}

}
