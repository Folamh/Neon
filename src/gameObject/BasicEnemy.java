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
	
<<<<<<< HEAD
	public BasicEnemy(PApplet p, int x, int y, Path path){
		super(p, x, y, path);
		speed = 4;
=======
	public BasicEnemy(PApplet p, Minim minim, int x, int y, Path path){
		super(p, minim, x, y, path);
		speed = 10;
>>>>>>> cb64c19dde3f2e143a57c9d65be1a605c746d402
		moving = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Moving", 5);
		still = new Animation(p, "Resources/Images/Enemy/BasicEnemy/Still", 10);
		mov = false;
		maxHealth = 100;
		health = 100;
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
