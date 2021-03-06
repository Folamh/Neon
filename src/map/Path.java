package map;

import java.util.ArrayList;
import processing.core.*;

//Class for holding paths to for game objects to follow
public class Path {
	
	//Class to hold path points
	public class PathPoint {
		//Order number in path
		int ppNum;
		//Location in path
		PVector ppLoc;
		
		//Taking in the order num and position of the path point
		private PathPoint(int ppNum, int x, int y) {
			this.ppNum = ppNum;
			ppLoc = new PVector(x,y);
		}
		
		//Returns the path point number
		public int getPPNum() {
			return ppNum;
		}
	}
	
	//For drawing and shit
	PApplet p;
	
	//ArrayList to hold the path
	ArrayList<PathPoint> path;
	
	public Path(PApplet p) {
		this.p = p;
		//Initializing the array list
		path = new ArrayList<PathPoint>();
	}
	
	//Sorts the path to be in order(low->high)
	public void sortPath() {
		for(int i = 2; i < path.size(); i++) {
			
			//Temp variable for swapping path points
			PathPoint temp;
			
			for(int j = i; j > 1 && path.get(j).getPPNum() < path.get(j-1).getPPNum(); j--) {
				temp = path.get(j);
				path.set(j , path.get(j-1));
				path.set(j-1, temp);
			}
		}
	}
	
	//Adds new point to the end of the path
	public void addPoint(int num, int x, int y) {
		//Temp path point variable
		PathPoint temp = new PathPoint(num,x,y);
		//Adding path point to the queue
		path.add(temp);
		sortPath();
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
	public int nextPoint(int curPoint) {
		return curPoint++;
	}
	
	//Decrements curPoint(updates prev and next point)
	public int prevPoint(int curPoint) {
		return curPoint--;
	}
	
	//Returns the current point(Currently traveling from)
	public PVector getCurPoint(int curPoint) {
		return path.get(curPoint).ppLoc;
	}
	
	//Returns the next point(Currently traveling to)
	public PVector getPrevPoint(int curPoint) {
		return path.get(curPoint-1).ppLoc;
	}
	
	//Returns the previous point(Previous current point)
	public PVector getNextPoint(int curPoint) {
		return path.get(curPoint+1).ppLoc;
	}
	
	//Returns the size of the path
	public int getPathSize(){
		return path.size();
	}
	
	//Returns the last point in the path
	public PVector getLastPoint() {
		return path.get(path.size()-1).ppLoc;
	}
	
	//Returns the first point in the path
	public PVector getFirstPoint() {
		return path.get(0).ppLoc;
	}
		
	//Displaying the path (for testing purposes)
	public void display() {
		for(PathPoint pp: path) {
			p.pushMatrix();
			p.translate(pp.ppLoc.x, pp.ppLoc.y);
			p.fill(255);
			p.noStroke();
			p.rect(0,0,10,10);
			p.popMatrix();
		}
	}
}
