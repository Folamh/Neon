package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	PVector nextPathPoint;
	float speed;
	
	Enemy(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	void moveToPathPoint(){
		if(pos.x != nextPathPoint.x){
			if((pos.x - nextPathPoint.x) < 0){
				pos.sub(speed, 0);
			}
			else{
				pos.add(speed, 0);
			}
			if(Math.abs(pos.x - nextPathPoint.x) <= speed){
				pos.x = nextPathPoint.x;
			}
		}
	}
}
