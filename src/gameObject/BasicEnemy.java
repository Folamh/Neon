package gameObject;

import java.util.ArrayList;

import animation.Animation;
import processing.core.PApplet;
import processing.core.PVector;

public class BasicEnemy extends Enemy{
	Animation moving;
	public BasicEnemy(PApplet p, int x, int y, ArrayList<PVector> path){
		super(p, x, y, path);
		speed = 4;
		moving = new Animation(p, "Resources/Images/Enemy/Basic Enemy/Moving", 1, pos);
		moving.loadImages();
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
		moving.displayAnimation();
		p.popMatrix();
	}
}
