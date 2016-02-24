package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class GameObject{
	PVector pos;
	PApplet p;
	
	GameObject(PApplet p, float x, float y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	
	public abstract void update();
	public abstract void render();
}
