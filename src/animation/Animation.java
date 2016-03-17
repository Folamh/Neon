package animation;

import java.io.File;
import processing.core.*;

public class Animation {
	//PApplet to allow drawing in the main window
	PApplet p;
	
	//file path for loading in the images
	String path;
	//array of images to hold frames of animation
	PImage[] images;
	
	//length of time the animation plays across
	double animTime;
	//time of the last frame swap
	int prevTime;
	
	//current frame index
	int curFrame;
	//Total number of frames
	int fCount;
	
	//taking in papplet and the number of frames
	public Animation(PApplet p, String path, double animTime) {
		//disambiguating passed in variables
		this.p = p;
		this.path = path;
		this.animTime = animTime;
		
		//initializing variables
		curFrame = 0;
		prevTime = 0;
		
		//loading in the images for the given path
		loadImages();
	}
	
	//loading in all the images for animation
	public void loadImages()
	{
		//file path for all the files for animation
		File path = new File(this.path);
		//array for all the files in the file path
		File[] f = path.listFiles();
		//initializing images array to number of files in path 
		images = new PImage[f.length];  
		
		//going through all the files in the path
		for(int i = 0; i < f.length; i++){
        	
			//checking if file names are valid files (.png files)
            if (f[i] != null && f[i].getName().endsWith(".png"))
            {
            	//TODO: Make it so we don't have to have file names really stupid
            	//path of specific image in animation
            	String url = this.path + "//" + i + ".png";

            	//System.out.println("image " + i + " loaded successfully");
            	images[i] = p.loadImage(url, "png");
            	//incrementing number of frames in animation
            	fCount++;
            	//System.out.println(images[i]);
            }
            else
            {
            	//printing out file names that aren't of .png type
            	System.out.println("ERROR: File ( " + f[i].getName() + " )not of png type.");
            }
            
        }
		
	}
	
	
	//Frame time is number 10ths of a second the animation will display across
	//Checking for swapping of next frame
	public void nextFrame() 
	{
		//getting the current number of milliseconds
		int count = p.millis();
		//making time accurate to a tenth of a second
		int time = count/100;
		//calculating time to wait for each frame
		int space = PApplet.round((float) (animTime/fCount));
		
		//checking if time has passed since last frame switch
		if(time%space == 0 && prevTime != time) 
		{
			//incrementing the current frame
			curFrame++;
			//looping current frame if it exceeds frame count
			curFrame = curFrame%fCount;
			//setting the time of frame switch
			prevTime = time;
		}
		
	}
	
	//displaying the current frame of the animation
	public void displayAnimation()
	{
		try
		{
			//displaying the current frame and incrementing to the next(if time has passed)
			p.image(images[curFrame],0,0);
			nextFrame();
		}
		catch(Exception e)
		{
			//error message when image fails to display (prints error and at what frame it failed)
			System.out.println("Error1: Failed to display image at frame " + curFrame);
		}
		
	}
}
