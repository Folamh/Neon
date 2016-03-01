package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class BasicEnemy extends Enemy{
	
	BasicEnemy(PApplet p, int x, int y, ArrayList<PVector> path){
		super(p, x, y, path);
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
