package gameObject;

import processing.core.*;

public class PlasmaTower extends Tower{
	PVector headPoint;
	
	PlasmaTower(int x, int y){
		super(x, y);
	}
	
	void update(){
		
	}
	
	void render(){
		//base turret goes here
		float angle = PVector.angleBetween(aim, defaultPlane);
		if(aim.y < 0) angle = - angle;
		pushMatrix();
		translate(headPoint.x, headPoint.y);
		rotate(angle);
		//turret head goes here
		popMatrix();
	}
}