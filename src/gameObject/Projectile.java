package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Projectile extends GameObject{
	float speed;
	Enemy target;
	
	Projectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y);
		this.target = target;
	}
	
	void moveToTarget(){
		PVector move;
		float angle = PVector.angleBetween(pos, target.pos);
		move = PVector.fromAngle(angle);
		move.setMag(speed);
		pos.add(move);
	}
}
