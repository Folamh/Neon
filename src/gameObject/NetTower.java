package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public class NetTower extends Tower{
	PVector headPoint;
	float angle;
	
	NetTower(PApplet p, int x, int y){
		super(p, x, y);
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
		//base turret goes here
		p.pushMatrix();
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		//turret head goes here
		p.popMatrix();
		p.popMatrix();
	}
	
	void shoot(){
		aim.set(leadTarget.pos);
		if(shootTimer < fireRate) {
			shootTimer++; 
		} else {
			projectiles.add(new PlasmaProjectile(p, headPoint.x, headPoint.y));
			shootTimer = 0;
		}
		
	}
}
