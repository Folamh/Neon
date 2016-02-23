package gameObject;

import java.util.ArrayList;

import processing.core.*;

public class PlasmaTower extends Tower{
	PVector headPoint;
	float angle;
	
	PlasmaTower(float x, float y){
		super(x, y);
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
	}
	
	void update(){
		
	}
	
	void render(){
		//base turret goes here
		angle = PVector.angleBetween(aim, defaultPlane);
		if(aim.y < 0) angle = - angle;
		pushMatrix();
		translate(headPoint.x, headPoint.y);
		rotate(angle);
		//turret head goes here
		popMatrix();
	}
	
	void shoot(){
		projectiles.add(new PlasmaProjectile(headPoint.x, headPoint.y, leadTarget));
	}
}
