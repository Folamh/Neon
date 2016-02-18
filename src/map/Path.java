package map;

import java.util.ArrayList;
import processing.core.*;

//Class for holding paths to for game objects to follow
public class Path {
	
	//ArrayList to hold the path
	ArrayList<PVector> path;
	
	int prevPoint; //Previous current point
	int curPoint; //Point traveling from
	int nextPoint; //Point traveling to
	
	Path() {
		//Initializing the array list
		path = new ArrayList<PVector>();
		
		curPoint = 0;
		prevPoint = curPoint - 1;
		nextPoint = curPoint + 1;
	}
	
	//Adds new point to the end of the path
	public void addPoint(int x, int y) {
		PVector temp = new PVector(x,y);
		path.add(temp);
	}
	
	//Removes path point at location x
	public void remPathPoint(int x) {
		path.remove(x);
	}
	
	//Clears the path of all path points
	public void clearPath() {
		path.clear();
	}
	
	//Increments curPoint(updates prev and next point)
	public void nextPoint() {
		curPoint ++;
		prevPoint = curPoint - 1;
		nextPoint = curPoint + 1;
	}
	
	//Decrements curPoint(updates prev and next point)
	public void prevPoint() {
		curPoint --;
		prevPoint = curPoint - 1;
		nextPoint = curPoint + 1;
	}
	
	//Returns the current point(Currently traveling from)
	public PVector getCurPoint() {
		return path.get(curPoint);
	}
	
	//Returns the next point(Currently traveling to)
	public PVector getPrevPoint() {
		return path.get(nextPoint);
	}
	
	//Returns the previous point(Previous current point)
	public PVector getNextPoint() {
		return path.get(prevPoint);
	}
}
