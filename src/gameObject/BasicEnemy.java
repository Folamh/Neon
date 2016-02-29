package gameObject;

import processing.core.PApplet;

public class BasicEnemy extends Enemy{
	
	BasicEnemy(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	public void update(){
		if(pos.x != nextPathPoint.x){
			moveToPathPoint();
		}
		else{
			takeElevator();
		}
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
