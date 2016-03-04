package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class PlasmaTower extends Tower{
	PVector headPoint;
	float angle;
	PImage base;
	PImage head;
	
	int wait;
	
	public PlasmaTower(PApplet p, float x, float y){
		super(p, x, y);
		base = p.loadImage("Resources\\Images\\Turret\\Basic Turret\\0.png");
		head = p.loadImage("Resources\\Images\\Turret\\Basic Turret\\1.png");
		headPoint = new PVector(pos.x, pos.y - 10);
		range = 250;
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
		wait = 0;
	}
	
	public void update(){
		if(leadTarget != null){
			shoot();
			
			PVector o = PVector.sub(leadTarget.pos, pos);
			angle = PApplet.atan2(o.y, o.x) - PApplet.HALF_PI;
			
			if(aim.y < 0) angle = - angle;
			
		} else {
			
			angle = 0;
			
		}
		
		for(int i = 0; i < projectiles.size(); i++){
				projectiles.get(i).moveToTarget(leadTarget);
		}
		
		cleanProjectiles();
	}
	
	public void render(){
		p.pushMatrix();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render();
		}
		p.image(base, pos.x, pos.y);
		
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		p.image(head, 0, 10);
		p.popMatrix();
	}
	
	void shoot(){
		aim.set(leadTarget.pos);
		if(wait < 30) {
			wait++; 
		} else {
			projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y));
			wait = 0;
		}
		
	}
}
