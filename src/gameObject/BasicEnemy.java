package gameObject;

import processing.core.PApplet;

public class BasicEnemy extends Enemy{
	
	BasicEnemy(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	public void update(){
		moveToPathPoint();
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
