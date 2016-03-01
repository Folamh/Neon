package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class TankEnemy extends Enemy{
	
	TankEnemy(PApplet p, int x, int y, ArrayList<PVector> path){
		super(p, x, y, path);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
