package animation;

import java.io.File;
import java.util.ArrayList;


import processing.core.PApplet;
import processing.core.PImage;

public class Animation extends PApplet{
	PImage [] basicEnemyMoving = new PImage[7];
	PImage [] basicEnemyStill = new PImage[6];
	PApplet p;
	File[] f;
	ArrayList<String> resultList;
	//Current frame index
	public int curFrame;
	//Total number of frames
	int fCount;
	//Taking in papplet and the number of frames
	public Animation(PApplet p, int fCount) {
		this.p = p;
		this.fCount = fCount;
		
		curFrame = 0;
	}
	
	public void loadImages()
	{
		sketchPath("");
		System.out.println(sketchPath(""));
		File path = new File("C:/Users/Hashdog/Documents/Neon/src/data/Enemy/Basic Enemy/Moving");
		int i = 0;
		f = path.listFiles();
        for (File file : f) {
        	
        	
            if (file != null && file.getName().endsWith(".png"))
            {
            	
            	System.out.println("image " + i + " loaded successfully");
            	basicEnemyMoving[i] = loadImage("Resources/Images/Enemy/Basic Enemy/Moving/" + i + ".png");
				
            	i++;
            }
            
        }
		
	}
	
	//Checking for swapping of next frame
	public void nextFrame() 
	{
		//Calculating number of frames to wait to swap frame
		int count = (int) (p.frameCount%p.frameRate);
		//Making count a multiple of fCount
		count -= count%fCount;
		
		//Checking if count frames have passed
		if(count%fCount == 0) 
		{
			curFrame++;
			curFrame = curFrame%fCount;
			
		}
		
	}
	
	//Returning the index of the current frame
	public int getCurFrame() {
		
		return curFrame;
	}
	
	public void displayAnimation()
	{
		
		
	}
}
