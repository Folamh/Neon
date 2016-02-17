package gameObject;

import processing.core.*;

public abstract class GameObject {
	PVector pos;
	
	GameObject() {
		
		
	}
	
	
	abstract void update();
	abstract void render();
}
