package gameObject;

import processing.core.*;

public abstract class GameObject{
	PVector pos;
	PApplet p;
	
	GameObject(PApplet p, int x, int y) {
		pos = new PVector(x,y);
		this.p = p;
	}
	
	
	abstract void update();
	abstract void render();
}
