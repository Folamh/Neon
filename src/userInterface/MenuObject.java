package userInterface;

import processing.core.*;

public abstract class MenuObject {
	
	//Position of the menu object
	PVector pos;
	
	//PApplet for drawing
	PApplet p;
	
	//Width and height of the object
	float objWidth, objHeight;
	
	//Rotation of the button
	float theta;
	
	//Booleans for mouse events
	boolean hover;
	boolean pressed;
	boolean released;
	
	MenuObject(PApplet p, float x, float y, int w, int h) {
		//Initializing the position of the object
		//pos = new PVector(x,y);
		pos.x = x;
		pos.y = y;
		
		//Initializing the height and width of the object
		objWidth = w;
		objHeight = h;
		
		//Initializing the rotation of the object
		theta = 0;
		
		//Initializing mouse/object state variables
		hover = false;
		pressed = false;
		released = true;
	}
	
	//Function for checking the mouse in relation to the object
	public void mouseListener() {
		
		//Checking if the mouse is within the bounds of the object
		if((p.mouseX <= pos.x+(objWidth/2)) && (p.mouseX >= pos.x-(objWidth/2)) && (p.mouseY <= pos.y+(objHeight/2)) && (p.mouseY >= pos.y-(objHeight/2))) {
			hover = true;
			
			//Checking if the mouse is pressed
			if(p.mousePressed) {
				pressed = true;
				released = false;
			} else {
				pressed = false;
				released = true;
			}
			
		} else {
			hover = false;
			pressed = false;
			released = true;
		}
	}
	
	//Setting the position of the object
	public void setPos(int x, int y) {
		pos = new PVector(x,y);
	}
	
	//Setting the rotation of the object
	public void setRot(float theta) {
		this.theta = theta;
	}
	
	//Setting the dimensions of the object
	public void setDim(int w, int h) {
		objWidth = w;
		objHeight = h;
	}
	
	abstract void update();
	abstract void render();
}
