package control;

import processing.core.*;

public class Camera {
	
	PApplet p;
	
	int curX, offSetX;
	int curY, offSetY;
	
	Camera(PApplet p) {
		this.p = p;
		
		offSetX = 0;
		offSetY = 0;
	}
	
	//Checking if mouse is pressed
	public void mousePressed() {
		curX = p.mouseX;
		curY = p.mouseY;
	}
	
	//Checking if the mouse is dragged
	public void mouseDragged() {
		calcOffSet();
	}
	
	//Checking if the mouse is released
	public void mouseReleased() {
		calcOffSet();
	}
	
	
	//Calculating the off set
	public void calcOffSet() {
		int x = p.mouseX;
		int y = p.mouseY;
		
		offSetX = curX - x;
		offSetY = curY - y;
	}
	
	//Returning the off set
	public PVector getOffSet() {
		PVector p = new PVector(offSetX,offSetY);
		return p;
	}
}
