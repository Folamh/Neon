package control;

import processing.core.*;
import java.util.ArrayList;
import animation.Animation;
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
	Animation animation;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		animation = new Animation(this, "Resources/Images/Enemy/Basic Enemy/Moving",1.5,new PVector(width/2,height/2));
		animation.loadImages();
		
	}
	
	public void draw() {
		background(255);
		map.render(false);

		animation.displayAnimation();

	}

<<<<<<< HEAD
=======
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
		size(1000,1021);
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	ArrayList<PVector> path;
	PImage building;
	BasicEnemy enemy;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		PVector e = new PVector(200, 200);
		path = new ArrayList<PVector>();
		path.add(e);
		building = loadImage("C:\\Users\\Ross\\Desktop\\ART\\Building.png");
		enemy = new BasicEnemy(this, 100, 100, path);

		imageMode(CENTER);
	}
	
	public void draw() {
		background(0);
		//map.render();
		this.image(building, width/2, height/2);
		enemy.render();
	}

>>>>>>> master
}
