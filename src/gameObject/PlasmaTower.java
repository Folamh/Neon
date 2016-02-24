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
		
	}
	
	public void render(){
		//base turret goes here
		angle = PVector.angleBetween(aim, defaultPlane);
		if(aim.y < 0) angle = - angle;
		p.pushMatrix();
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		//turret head goes here
		p.popMatrix();
	}
	
	void shoot(){
		aim.set(PVector.sub(headPoint, leadTarget.pos));
		projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y, leadTarget));
	}
}
