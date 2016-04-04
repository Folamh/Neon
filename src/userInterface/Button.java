package userInterface;

import processing.core.*;

public class Button extends MenuObject{
	
	//Image for the button
	PImage bImage;
	
	//Text for the button
	String text;
	
	//Value of the button
	int val;
	
	//Button with image
	public Button(PApplet p, int val, float x, float y, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,bImage.width,bImage.height);
		
		//Setting the button image
		this.bImage = bImage;
		
		//setting the text
		text = "";
		
		//Setting the value of the button
		this.val = val;
	}
	
	//Button with image and specified width and height
	public Button(PApplet p, int val, float x, float y, int w, int h, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,w,h);
		
		//Resizing the button image
		bImage.resize(w,h);
		
		//Setting the button image
		this.bImage = bImage;
		
		//Setting the text
		text = "";
		
		//Setting the value of the button
		this.val = val;
	}
	
	//Button with image and text
	public Button(PApplet p, int val, float x, float y, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,val,x,y,bImage);
		
		//Setting the text and text size
		this.text = text;
		p.textSize(size);
	}
	
	//Button with image, specified size and text
	public Button(PApplet p, int val, float x, float y, int w, int h, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,val,x,y,w,h,bImage);
		
		//Setting the text and text size
		this.text = text;
		p.textSize(size);
	}
	
	//Returning the value of the button
	public int getValue() {
		return val;
	}
	
	//Setting the new image and size for the new button
	public void setImage(PImage bImage, int w, int h) {
		//Resizing the image
		bImage.resize(w,h);
		//Setting the new image
		this.bImage = bImage;
	}
	
	//Setting a new image for the button(defaulting to old size)
	public void setImage(PImage bImage) {
		//Overriding method
		setImage(bImage, this.bImage.width, this.bImage.height);
	}
	
	//Returns the text inside the button
	public String getText() {
		return text;
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
