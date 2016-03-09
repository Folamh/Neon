package gameObject;

import java.util.ArrayList;

import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class PlasmaTower extends Tower{
	PVector headPoint;
	float angle;
	PImage base;
	PImage head;
	
	public PlasmaTower(PApplet p, Minim minim, float x, float y){
		super(p, minim, x, y);
		base = p.loadImage("resources/images/turret/basicturret/0.png");
		head = p.loadImage("resources/images/turret/basicturret/1.png");
		fireRate = 30;
		headPoint = new PVector(pos.x, pos.y - 50);
		range = 250;
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
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
				projectiles.get(i).moveToTarget();
		}
		
		cleanProjectiles();
	}
	
	public void render(){
		p.pushMatrix();
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render();
		}
		p.image(base, headPoint.x, headPoint.y);
		
		p.translate(headPoint.x, headPoint.y-10);
		p.rotate(angle);
		p.image(head, 0, 10);
		p.popMatrix();
	}
	
	public void shoot(){
		aim.set(leadTarget.pos);
		if(shootTimer < fireRate) {
			shootTimer++; 
		} else {
			projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y, leadTarget));
			shootTimer = 0;
			playShoot();
		}
		
	}
}
