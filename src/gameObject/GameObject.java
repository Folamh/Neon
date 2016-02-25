package gameObject;

import java.util.ArrayList;
import processing.core.*;

public abstract class GameObject{
<<<<<<< HEAD
	
	//ArrayList to hold the frames of each object
	ArrayList<PImage> frames;
	PVector pos;
	PApplet p;
=======
	PVector pos;//Position of the object.
	PApplet p;//The current sketch being drawn.
>>>>>>> master
	
	GameObject(PApplet p, float x, float y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	//Basic functions for all objects:
	public abstract void update();//Update positions.
	public abstract void render();//ALl forms of rendering go here.
}
