package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	PVector nextPathPoint;
	
	Enemy(PApplet p, int x, int y){
		super(p, x, y);
	}
}
