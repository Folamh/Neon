package userInterface;

import processing.core.*;

public class TextArea extends MenuObject{
	
	//Image for the button
	PImage bImage;
	
	//Text for the button
	String text;
	
	//TextArea with image
	public TextArea(PApplet p, float x, float y, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,bImage.width,bImage.height);
		
		//Setting the image and the default text
		this.bImage = bImage;

		text = "";

	}
	
	//TextArea with image and specified width and height
	public TextArea(PApplet p, float x, float y, int w, int h, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,w,h);
		
		//Resizing the button
		bImage.resize(w,h);
		//Setting the image and the default text
		this.bImage = bImage;

		text = " ";

	}
	
	//TextArea with image and text
	public TextArea(PApplet p, float x, float y, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,x,y,bImage);
		
		//Setting the text for the button
		this.text = text;
		p.textSize(size);
	}
	
	//TextArea with image, specified size and text
	public TextArea(PApplet p, float x, float y, int w, int h, PImage bImage, String text, int size) {
		//Constructor chaining
		this(p,x,y,w,h,bImage);
		
		//Setting the text for the button
		this.text = text;
		p.textSize(size);
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
	
	//Updating the text area
	public void update(){
		
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
