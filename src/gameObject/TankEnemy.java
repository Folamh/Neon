package gameObject;

import processing.core.PApplet;

public class TankEnemy extends Enemy{
	
	TankEnemy(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
