package gameObject;

import processing.core.PApplet;

public abstract class Projectile extends GameObject{
	int speed;
	Enemy target;
	
	Projectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y);
		this.target = target;
	}
	
	void moveToTarget(){
		
	}
}
