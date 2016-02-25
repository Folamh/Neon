package gameObject;

import java.util.ArrayList;
import processing.core.*;

public abstract class GameObject{
	
	//ArrayList to hold the frames of each object
	ArrayList<PImage> frames;
	PVector pos;
	PApplet p;
	
	GameObject(PApplet p, float x, float y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	
	public abstract void update();
	public abstract void render();
}
