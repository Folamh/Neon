package gameObject;

import java.util.ArrayList;
import processing.core.*;

public abstract class GameObject{
	
	//ArrayList to hold the frames of each object
	ArrayList<ArrayList<PImage>> idleAnim;
	ArrayList<ArrayList<PImage>> activeAnim;
	
	PVector pos;
	PApplet p;
	
	GameObject(PApplet p, float x, float y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	//Basic functions for all objects:
	public void update() {
		// TODO Auto-generated method stub
		
	}//Update positions.
	public abstract void render();//ALl forms of rendering go here.
}
