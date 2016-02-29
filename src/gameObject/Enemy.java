package gameObject;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	PVector nextPathPoint;
	float speed;
	boolean inElevator;
	
	Enemy(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	void moveToPathPoint(){
		if((pos.x != nextPathPoint.x) && (pos.y != nextPathPoint.y)){
			inElevator = false;
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
	
	void takeElevator(){
		if((pos.x == nextPathPoint.x) && (pos.y != nextPathPoint.y)){
			inElevator = true;
			if((pos.y - nextPathPoint.y) < 0){
				pos.sub(0, speed - ((speed/100)*50));
			}
			else{
				pos.add(0, speed - ((speed/100)*50));
			}
			if(Math.abs(pos.y - nextPathPoint.y) <= speed){
				pos.y = nextPathPoint.y;
			}
		}
	}
}
