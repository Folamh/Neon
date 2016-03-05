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
	boolean clicked;
	
	MenuObject(PApplet p, float x, float y, int w, int h) {
		//Initializing the papplet
		this.p = p;
		
		//Initializing the position of the object
		pos = new PVector(x,y);
		
		//Initializing the height and width of the object
		objWidth = w;
		objHeight = h;
		
		//Initializing the rotation of the object
		theta = 0;
		
		//Initializing mouse/object state variables
		hover = false;
		pressed = false;
		clicked = false;
	}
	
	//Function for checking the mouse in relation to the object
	public void mouseListener() {
		
		//Checking if the mouse is within the bounds of the object
		if(p.mouseX <= pos.x+(objWidth/2) && p.mouseX >= pos.x-(objWidth/2) && p.mouseY <= pos.y+(objHeight/2) && p.mouseY >= pos.y-(objHeight/2)) {
			hover = true;
			
			//Checking if the mouse is pressed
			if(p.mousePressed) {
				pressed = true;
				clicked = false;
			} else {
				
				//Checking if mouse was clicked
				if(pressed) { 
					clicked = true;
				} else {
					clicked = false;
				}
				
				pressed = false;
			}
			
		} else {
			hover = false;
			pressed = false;
			clicked = false;
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
	
	//Returning the state of hover
	public boolean getHover() {
		return hover;
	}
	
	//Returning the state of pressed
	public boolean getPressed() {
		return pressed;
	}
	
	//Returning the state of clicked
	public boolean getClicked() {
		return clicked;
	}
	
	
	//Abstarct methods for updating and rendering
	abstract void update();
	abstract void render();
}
