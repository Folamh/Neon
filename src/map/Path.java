package map;

import java.util.ArrayList;
import processing.core.*;

//Class for holding paths to for game objects to follow
public class Path {
	
	//Class to hold path points
	public class PathPoint {
		PVector pos;
		
		PathPoint(int x, int y) {
			pos.x = x;
			pos.y = y;
		}
	}
	
	//ArrayList to hold the path
	ArrayList<PathPoint> p;
	
	int prevPoint, curPoint, nextPoint;
	
	Path() {
		//Initializing the array list
		p = new ArrayList<PathPoint>();
		
		prevPoint = 0;
		curPoint = 0;
		nextPoint = 0;
	}
	
	//Adds new point to the end of the path
	public void addPoint(int x, int y) {
		
		PathPoint newPoint = new PathPoint(x,y);
		p.add(newPoint);
	}
	
	//Removes path point at location x
	public void remPathPoint(int x) {
		p.remove(x);
	}
	
	//Clears the path of all path points
	public void clearPath() {
		p.clear();
	}
	
	//Returns the path
	public ArrayList<PathPoint> getPath() {
		return p;
	}
}
