package gameObject;

import animation.Animation;
import ddf.minim.Minim;
import map.Path;
import processing.core.PApplet;

public class BasicEnemy extends Enemy{
	Animation moving;
	Animation still;
	Boolean mov;
	float curX;

	public BasicEnemy(PApplet p, Minim minim, int x, int y, Path path){
		super(p, minim, x, y, path);
		speed = 2;
		moving = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Moving", 5);
		still = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Still", 10);
		mov = false;
		maxHealth = 250;
		health = maxHealth;
		curX = 0;
	}
	
	public void displayHealth(int barWidth, int barHeight) {
		int curHealth = (int) PApplet.map(health,0,maxHealth,0,barWidth-2);
		
		p.fill(0);
		p.rectMode(PApplet.CENTER);
		p.rect(0,-70,barWidth,barHeight);
		p.fill(0,200,0);
		p.rect(0,-70,curHealth,barHeight-1);
	}
	
	public void update(){
		moveToPathPoint();
		takeElevator();
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
		displayHealth(50,5);
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
