package gameObject;

import animation.Animation;
import processing.core.PApplet;

public class PlasmaProjectile extends Projectile{
	Animation bullet;
	
	PlasmaProjectile(PApplet p, float x, float y, Enemy target){
		super(p, x, y, target);
		bullet = new Animation(p, "resources/Images/Projectiles/Bullet", 1);
		speed = 10;
		bullet.loadImages();
		damage = 10;
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		p.translate(pos.x, pos.y);
		p.rotate(angle);
		bullet.displayAnimation();
		p.popMatrix();
	}
}
