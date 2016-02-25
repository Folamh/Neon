package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class PlasmaTower extends Tower{
	PVector headPoint;
	float angle;
	
	PlasmaTower(PApplet p, float x, float y){
		super(p, x, y);
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
	}
	
	public void update(){
		shoot();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		cleanProjectiles();
	}
	
	public void render(){
		p.pushMatrix();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render();
		}
		//base turret goes here
		angle = PVector.angleBetween(aim, defaultPlane);
		if(aim.y < 0) angle = - angle;
		p.pushMatrix();
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		//turret head goes here
		p.popMatrix();
		p.popMatrix();
	}
	
	void shoot(){
		aim.set(PVector.sub(headPoint, leadTarget.pos));
		projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y, leadTarget));
	}
}
