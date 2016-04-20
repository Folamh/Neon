package gameObject;

import ddf.minim.Minim;
import map.Path;
import processing.core.PApplet;

public class TankEnemy extends Enemy{
	
	public TankEnemy(PApplet p, Minim minim, int x, int y, Path path){
		super(p, minim, x, y, path);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
