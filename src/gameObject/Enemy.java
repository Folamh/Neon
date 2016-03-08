package gameObject;

import map.Path;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	PVector nextPathPoint;
	public Path path;
	int curPoint;
	float speed;
	boolean inElevator;
	
	public boolean gotData;
	int wait;
	
	int health;
	
	Enemy(PApplet p, int x, int y, Path path){
		super(p, x, y);
		this.path = path;
		curPoint = 0;
		nextPathPoint = path.getNextPoint();
		inElevator = false;
	}
	
	//Moves the enemy towards their next path point
	void moveToPathPoint(){
		if((pos.x != nextPathPoint.x) && (pos.y == nextPathPoint.y)){
			inElevator = false;//Takes them out of the elevator
			if((pos.x - nextPathPoint.x) > 0){//Left or right movement
				pos.sub(speed, 0);
			}
			else{
				pos.add(speed, 0);
			}
			if(Math.abs(pos.x - nextPathPoint.x) <= speed){//If they are near there next point
				pos.x = nextPathPoint.x;//Move onto point
				nextPoint();//Change next point
			}
		}
	}
	
	void takeElevator(){
		if((pos.x == nextPathPoint.x) && (pos.y != nextPathPoint.y)){
			inElevator = true;
			if((pos.y - nextPathPoint.y) > 0){
				pos.sub(0, speed - ((speed/100)*50));
			}
			else{
				pos.add(0, speed - ((speed/100)*50));
			}
			if(Math.abs(pos.y - nextPathPoint.y) <= speed){
				pos.y = nextPathPoint.y;
				nextPoint();
			}
		}
	}
	
	void nextPoint(){
		if(gotData){
			if(++curPoint < path.getSize()){
				nextPathPoint = path.getPrevPoint();
			}
		}
		else{
			if(++curPoint < path.getSize()){
				nextPathPoint = path.getNextPoint();
			}
		}
	}
}
