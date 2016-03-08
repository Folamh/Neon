package gameObject;

import map.Path;
import processing.core.PApplet;

public class TankEnemy extends Enemy{
	
	TankEnemy(PApplet p, int x, int y, Path path){
		super(p, x, y, path);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
