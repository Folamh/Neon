package animation;

import processing.core.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Animation
{
	PApplet p;
	ArrayList<Image> images = new ArrayList<Image>();
	int counter = frameCount%frameRate;
		
	Animation(PApplet p){
		this.p = p;
		
	}
	public void loadImages()
	{
		
	
	}
	public void currentFrame()
	{
		
	}

}
