package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public class NetTower extends Tower{
	PVector headPoint;
	
	NetTower(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	void update(){
		
	}
	
	void render(){
		//base turret goes here
		float angle = PVector.angleBetween(aim, defaultPlane);
		if(aim.y < 0) angle = - angle;
		p.pushMatrix();
		p.translate(headPoint.x, headPoint.y);
		p.rotate(angle);
		//turret head goes here
		p.popMatrix();
	}
}
