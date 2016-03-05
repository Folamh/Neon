package userInterface;

import processing.core.*;

public class Button extends MenuObject{
	
	//Image for the button
	PImage bImage;
	
	//Text for the button
	String text;
	
	//Button with image
	public Button(PApplet p, float x, float y, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,bImage.width,bImage.height);
		
		//Setting the image and the default text
		this.bImage = bImage;
		text = "DEFAULT";
	}
	
	//Button with image and specified width and height
	public Button(PApplet p, float x, float y, int w, int h, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,w,h);
		//Resizing the button
		bImage.resize(w,h);
		//Setting the image and the default text
		this.bImage = bImage;
		text = "DEFAULT";
	}
	
	//Button with image and text
	public Button(PApplet p, float x, float y, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,x,y,bImage);
		
		//Setting the text for the button
		this.text = text;
		p.textSize(size);
	}
	
	//Button with image, specified size and text
	public Button(PApplet p, float x, float y, int w, int h, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,x,y,w,h,bImage);
		
		//Setting the text for the button
		this.text = text;
		p.textSize(size);
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
