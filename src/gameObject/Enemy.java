package gameObject;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import map.Path;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	//Path object
	Path path;
	
	//Position of next path point
	PVector nextPathPoint, vel;
	
	//If the enemy is in an elevator
	public boolean inElevator;
	
	//If the enemy has stolen some data
	boolean gotData;
	boolean stoleData;
	
	//Max speed and current speed of the enemy
	float speed, curSpeed;
	//Wait period for spawning enemies
	int wait;
	//Health of the enemy
	int health,maxHealth;
	//Current point of the enemy
	int curPoint;

	//SFX
	Minim minim;
	AudioPlayer hack;
	AudioPlayer hurt;
	
	//Constructor
	Enemy(PApplet p, Minim minim, int x, int y, Path path){
		//Calling game object constructor
		super(p, x, y);
		//Setting the path
		this.path = path;
		//Setting up Minim and SFX
		this.minim = minim;
		hack = this.minim.loadFile("Resources/Audio/HACKED.wav");
		hurt = this.minim.loadFile("Resources/Audio/Hurt.wav");
		//Setting the first pathPoint
		nextPathPoint = path.getFirstPoint();
		//Setting enemy state to not in elevator
		inElevator = false;
		
		gotData = false;
		stoleData = false;
		
		curPoint = 0;
		
		//Initializing vel
		vel = PVector.sub(nextPathPoint, pos);
		
		//Normalizing velocity to 1
		vel.normalize();
		//Multiplying the velocity by the enemie's speed
		vel.mult(speed);
	}
	
	//play hacking noise
	void playHack(){
		hack.rewind();
		hack.play();
	}
	
	//play hurt noise
	void playHurt(){
		hurt.rewind();
		hurt.play();
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
			pos = nextPathPoint;
			nextPoint();
		} else {
			//Calculating the velocity vector for moving the enemy
			vel = PVector.sub(nextPathPoint, pos);
			
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
		if(pos.x == nextPathPoint.x && pos.y != nextPathPoint.y){
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
			if(curPoint > 0) {
				nextPathPoint = path.getPrevPoint(curPoint);
				curPoint--;
			} else {
				//TODO change this to do something useful
				gotData = false;
				stoleData = true;
			}
		}
		else{
			//Checking if enemy is at the end of the path
			if(curPoint < path.getPathSize()-1) { 
				nextPathPoint = path.getNextPoint(curPoint);
				curPoint++;
			} else {
				playHack();
				gotData = true;
			}
		}
	}
	
	//Returning weather the enemy has stolen the data
	public boolean getStoleData() {
		return stoleData;
	}
	
	//Damages the enemies health
	public void damage(int dmg) {
		health -= dmg;
		playHurt();
	}
	
	//Returns the health of the enemy
	public int getHealth() {
		return health;
	}
}
