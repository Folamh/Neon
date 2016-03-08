package gameObject;

import map.Path;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	//Path object
	public Path path;
	
	//Position of next path point
	PVector nextPathPoint;
	
	//If the enemy is in an elevator
	boolean inElevator;
	
	//If the enemy has stolen some data
	public boolean gotData;
	
	//Max speed and current speed of the enemy
	float speed, curSpeed;
	//Wait period for spawning enemies
	int wait;
	//Health of the enemy
	int health;
	
	//Constructor
	Enemy(PApplet p, int x, int y, Path path){
		//Calling game object constructor
		super(p, x, y);
		//Setting the path
		this.path = path;
		//Setting the first pathPoint
		nextPathPoint = path.getFirstPoint();
		//Setting enemy state to not in elevator
		inElevator = false;
	}
	
	//Moves enemy towards the next path point
	void moveToPathPoint(){
		//Setting the current speed
		curSpeed = speed;
		
		//Halving the speed if in elevator
		if(inElevator) {
			curSpeed /= 2;
		}
		
		//Checking if enemy is at next path point
		if(pos.x <= nextPathPoint.x + speed && pos.x >= nextPathPoint.x - speed && pos.y <= nextPathPoint.y + speed && pos.y >= nextPathPoint.y - speed) {
			//Setting the next point
			nextPoint();
		} else {
			//Calculating the velocity vector for moving the enemy
			PVector vel = PVector.sub(nextPathPoint, pos);
			
			//Normalizing velocity to 1
			vel.normalize();
			//Multiplying the velocity by the enemie's speed
			vel.mult(speed);
			
			//Moving the enemy by the speed
			pos.add(vel);
		}
	}
	
	//Checking if the enemy is in an elevator
	void takeElevator(){
		if(pos.x <= nextPathPoint.x + speed && pos.x >= nextPathPoint.x - speed){
			inElevator = true;
		} else {
			inElevator = false;
		}
	}
	
	//Setting the next point for the enemy to go to
	void nextPoint(){
		//Checking if the enemy has data
		if(gotData){
			//Checking if the enemy is back at start of path
			if(nextPathPoint != path.getFirstPoint()) {
				nextPathPoint = path.getPrevPoint();
				path.prevPoint();
			} else {
				//TODO change this to do something useful
				gotData = false;
			}
		}
		else{
			//Checking if enemy is at the end of the path
			if(nextPathPoint != path.getLastPoint()) { 
				nextPathPoint = path.getNextPoint();
				path.nextPoint();
			} else {
				gotData = true;
			}
		}
	}
}
