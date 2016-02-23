package gameObject;

import processing.core.*;

public abstract class GameObject extends PApplet{
	PVector pos;
	
	GameObject(float x, float y) {
		pos.x = x;
		pos.y = y;
	}
	
	
	abstract void update();
	abstract void render();
}
