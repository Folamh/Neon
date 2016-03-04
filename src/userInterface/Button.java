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
		
		this.bImage = bImage;
		text = "";
	}
	
	//Button with image and specified width and height
	public Button(PApplet p, float x, float y, int w, int h, PImage bImage) {
		//Calling the super constructor
		super(p,x,y,w,h);
		//Resizing the button
		bImage.resize(w,h);
		//Disambiguating bImage
		this.bImage = bImage;
		text = "";
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
	
	public void update(){
		mouseListener();
	}
	
	public void render() {
		p.pushMatrix();
		p.translate(pos.x,pos.y);
		p.rotate(theta);
		p.image(bImage,0,0);
		p.textAlign(PApplet.CENTER);
		p.fill(0);
		p.text(text,0,0);
		p.popMatrix();
	}
}
