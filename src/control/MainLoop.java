<<<<<<< HEAD
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
	
	//PVector to hold the offset for everything on the screen
	PVector off;
	ArrayList<Tile> tiles;
	
	//Only use this for initializing variables
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
		
		cam.update(off,1);
		off = cam.getOffSet();
		
		pushMatrix();
		translate(-off.x,-off.y);
		map.render(false);
		popMatrix();
		
	}
=======
>>>>>>> master

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
		size(500,500);
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	Animation animation;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		animation = new Animation(this, "Resources/Images/Enemy/Basic Enemy/Moving",1.5,);
		animation.loadImages();
		
	}
	
	public void draw() {
		background(255);
		map.render();

		animation.displayAnimation();

	}

}
