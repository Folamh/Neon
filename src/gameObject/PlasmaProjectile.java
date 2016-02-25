package gameObject;

import processing.core.PApplet;

public class PlasmaProjectile extends Projectile{
	
	PlasmaProjectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y, target);
	}
	
	public void update(){
		moveToTarget();
	}
	
	public void render(){
		p.pushMatrix();
		//
		p.popMatrix();
	}
}
