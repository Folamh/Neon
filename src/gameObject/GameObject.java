package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class GameObject{
	PVector pos;//Position of the object.
	PApplet p;//The current sketch being drawn.
	
	GameObject(PApplet p, float x, float y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	//Basic functions for all objects:
	public abstract void update();//Update positions.
	public abstract void render();//ALl forms of rendering go here.
}
