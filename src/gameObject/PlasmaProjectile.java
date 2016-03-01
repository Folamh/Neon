package gameObject;

import animation.Animation;
import processing.core.PApplet;

public class PlasmaProjectile extends Projectile{
	Animation bullet;
	
	PlasmaProjectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y, target);
		bullet = new Animation(p, "D:\\Neon\\Resources\\Images\\Projectiles\\Bullet", 1, pos);
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
