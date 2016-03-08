package gameObject;

import animation.Animation;
import map.Path;
import processing.core.PApplet;

public class BasicEnemy extends Enemy{
	Animation moving;
	Animation still;
	Boolean mov;
	float curX;
	
	public BasicEnemy(PApplet p, int x, int y, Path path){
		super(p, x, y, path);
		speed = 10;
		moving = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Moving", 5);
		still = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Still", 10);
		mov = false;
		health = 100;
		curX = 0;
	}
	
	public void update(){
		
		moveToPathPoint();
		
		if(!inElevator){
			mov = true;
		}
		else{
			mov = false;
		}
		
	}
	
	public void render(){
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		if(mov){
			if(vel.x > 0) {
				p.scale(-1.0f,1.0f);
			} else if(vel.x < 0) {
				p.scale(1.0f,1.0f);
			}
			moving.displayAnimation();
		}
		else{
			still.displayAnimation();
		}
		p.popMatrix();
	}
}
