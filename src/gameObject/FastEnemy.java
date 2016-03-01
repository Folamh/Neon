package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class FastEnemy extends Enemy{
	
	FastEnemy(PApplet p, int x, int y, ArrayList<PVector> path){
		super(p, x, y, path);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		
		p.popMatrix();
	}
}
