package userInterface;

import processing.core.*;

public class Button extends MenuObject{
	
	//Image for the button
	PImage bImage;
	
	Button(PApplet p, float x, float y, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,bImage.width,bImage.height);
		
		this.bImage = bImage;
	}
	
	public void update(){
		mouseListener();
	}
	
	public void render() {
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		p.rotate(theta);
		p.image(bImage,0,0);
		p.popMatrix();
	}
}
