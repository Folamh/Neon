package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Projectile extends GameObject{
	float speed;
	float angle;
	Enemy target;
	PVector o;
	int damage;
	
	Projectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y);
		this.target = target;
	}
	
	void moveToTarget(){
		if(target != null) {
			o = target.pos;
		}
		PVector vel = PVector.sub(o, pos);
		angle = PApplet.atan2(vel.y, vel.x) + PApplet.HALF_PI;
		
		vel.normalize();
		vel.mult(speed);
		
		pos.add(vel);
	}
	
	//Returns the damage of the projectile
	public int getDamage() {
		return damage;
	}
}
