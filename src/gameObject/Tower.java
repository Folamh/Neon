package gameObject;

import processing.core.*;

public  abstract class Tower extends GameObject{
	PVector aim;
	PVector defaultPlane;
	
	Tower(int x, int y){
		super(x, y);
		defaultPlane = new PVector(0, 0);
	}
	
	abstract void update();
	
	abstract void render();
}
