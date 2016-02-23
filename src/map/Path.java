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
		
		PathPoint(int ppNum, int x, int y) {
			this.ppNum = ppNum;
			ppLoc = new PVector(x,y);
		}
		
		//Returns the path point number
		public int getPPNum() {
			return ppNum;
		}
	}
	
	
	
	//ArrayList to hold the path
	ArrayList<PathPoint> path;
	
	int prevPoint; //Previous current point
	int curPoint; //Point traveling from
	int nextPoint; //Point traveling to
	
	Path() {
		//Initializing the array list
		path = new ArrayList<PathPoint>();
		
		curPoint = 0;
		prevPoint = curPoint - 1;
		nextPoint = curPoint + 1;
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
		return path.get(curPoint).ppLoc;
	}
	
	//Returns the next point(Currently traveling to)
	public PVector getPrevPoint() {
		return path.get(nextPoint).ppLoc;
	}
	
	//Returns the previous point(Previous current point)
	public PVector getNextPoint() {
		return path.get(prevPoint).ppLoc;
	}
}
