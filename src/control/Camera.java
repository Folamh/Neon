package control;

import processing.core.*;

public class Camera {
	
	//PApplet from the main loop
	PApplet p;
	
	int curX, offSetX, setX;
	int curY, offSetY, setY;
	
	boolean r;
	
	Camera(PApplet p) {
		this.p = p;
		
		offSetX = 0;
		offSetY = 0;
		setX = 0;
		setY = 0;
		
		r = false;
	}
	
	//Updating the camera
	public void update(PVector off) {
		
		//Checking for the initial click
		if(p.mousePressed && !r) {
			r = true;
			startLoc(off);
		}
		
		//Calculating the off set
		if(p.mousePressed) {
			calcOffSet();
		} else {
			r = false;
		}
	}
	
	//Get initial location
	public void startLoc(PVector off) {
		//Getting the current off set
		setX = (int) off.x;
		setY = (int) off.y;
		
		//Getting the current position of the mouse
		curX = p.mouseX;
		curY = p.mouseY;
	}
	
	//Calculating the off set
	public void calcOffSet() {
		//Setting offset relative to old off set
		offSetX = setX + (curX - p.mouseX);
		offSetY = setX + (curY - p.mouseY);
	}
	
	//Returning the off set
	public PVector getOffSet() {
		PVector p = new PVector(offSetX,offSetY);
		return p;
	}
}
