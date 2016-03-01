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
	
	public PlasmaTower(PApplet p, float x, float y){
		super(p, x, y);
		base = p.loadImage("Resources\\Images\\Turret\\Basic Turret\\0.png");
		head = p.loadImage("Resources\\Images\\Turret\\Basic Turret\\1.png");
		headPoint = new PVector(pos.x, pos.y - 10);
		range = 100;
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
	}
	
	public void update(){
		if(leadTarget != null){
			shoot();
			angle = PVector.angleBetween(leadTarget.pos, pos);
			if(aim.y < 0) angle = - angle;
		}
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
		p.image(base, pos.x, pos.y);
		
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		p.image(head, 0, 10);
		p.popMatrix();
	}
	
	void shoot(){
		aim.set(leadTarget.pos);
		projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y, leadTarget));
	}
}
