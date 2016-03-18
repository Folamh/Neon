package userInterface;

import processing.core.*;

public class Button extends TextArea{
	
	//Value of the button
	int val;
	
	//Button with image
	public Button(PApplet p, int val, float x, float y, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,bImage);
		
		//Setting the value of the button
		this.val = val;
	}
	
	//Button with image and specified width and height
	public Button(PApplet p, int val, float x, float y, int w, int h, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,w,h,bImage);
		//Setting the value of the button
		this.val = val;
	}
	
	//Button with image and text
	public Button(PApplet p, int val, float x, float y, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,val,x,y,bImage);
	}
	
	//Button with image, specified size and text
	public Button(PApplet p, int val, float x, float y, int w, int h, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,val,x,y,w,h,bImage);
	}
	
	//Returning the value of the button
	public int getValue() {
		return val;
	}
	
	//Updating the button
	public void update(){
		mouseListener();
	}
	
	//Rendering the button
	public void render() {
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		p.rotate(theta);
		p.image(bImage,0,0);
		p.textAlign(PApplet.CENTER, PApplet.CENTER);
		p.fill(0);
		p.text(text,0,0);
		p.popMatrix();
	}
}
