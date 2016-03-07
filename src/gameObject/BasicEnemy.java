package gameObject;

import animation.Animation;
import map.Path;
import processing.core.PApplet;

public class BasicEnemy extends Enemy{
	Animation moving;
	Animation still;
	Boolean mov;
	public BasicEnemy(PApplet p, int x, int y, Path path){
		super(p, x, y, path);
		speed = 2;
		moving = new Animation(p, "Resources/Images/Enemy/Basic Enemy/Moving", 1);
		still = new Animation(p, "Resources/Images/Enemy/Basic Enemy/Still", 1);
		moving.loadImages();
		still.loadImages();
		mov = false;
		health = 100;
	}
	
	public void update(){
		if(pos.x != nextPathPoint.x){
			moveToPathPoint();
			mov = true;
		}
		else{
			takeElevator();
			mov = false;
		}
		
	}
	
	public void render(){
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		if(mov){
			moving.displayAnimation();
		}
		else{
			still.displayAnimation();
		}
		p.popMatrix();
	}
}
