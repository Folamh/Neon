package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Projectile extends GameObject{
	float speed;
	float angle;
	Enemy target;
	PVector o;
	
	Projectile(PApplet p, float x, float y){
		super(p, x, y);
	}
	
	void moveToTarget(Enemy target){
		this.target = target;
		if(target != null) {
			o = target.pos;
		}
		PVector vel = PVector.sub(o, pos);
		angle = PApplet.atan2(vel.y, vel.x) + PApplet.HALF_PI;
		
		vel.normalize();
		vel.mult(speed);
		
		pos.add(vel);
	}
}
