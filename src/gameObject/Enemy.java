package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Enemy extends GameObject{
	PVector nextPathPoint;
	ArrayList<PVector> path;
	int curPoint;
	float speed;
	boolean inElevator;
	
	int data;
	int maxData;
	boolean gatherData;
	boolean doneGathering;
	int wait;
	
	Enemy(PApplet p, int x, int y, ArrayList<PVector> path){
		super(p, x, y);
		this.path = path;
		curPoint = 0;
		nextPathPoint = path.get(curPoint);
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
				nextPoint();
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
				nextPoint();
			}
		}
	}
	
	void nextPoint(){
		if(++curPoint < path.size()){
			nextPathPoint = path.get(curPoint);
		}
	}
	
	public void newPath(ArrayList<PVector> path){
		curPoint = 0;
		this.path = path;
		nextPathPoint = path.get(curPoint);
	}
	
	void collectData(){
		if(gatherData){
			if(wait < 8){//TODO add time.
				wait++;
			}
			else if(data < maxData){
				data++;
			}
			else{
				doneGathering = true;
			}
		}
	}
}
