package gameObject;

import animation.Animation;
import processing.core.PApplet;

public class PlasmaProjectile extends Projectile{
	Animation bullet;
	
	PlasmaProjectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y, target);
		bullet = new Animation(p, "Resources\\Images\\Projectiles\\Bullet", 1, pos);
		speed = 5;
	}
	
	public void update(){
		moveToTarget();
	}
	
	public void render(){
		p.pushMatrix();
		bullet.displayAnimation();
		p.popMatrix();
	}
}
