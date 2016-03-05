package animation;

import java.io.File;


import processing.core.*;

public class Animation{
	public PImage [] images;
	public PImage background;
	PApplet p;
	File[] f;
	String path;
	double frameTime;
	//Current frame index
	public int curFrame;
	//Total number of frames
	int fCount;
	//Taking in papplet and the number of frames
	public Animation(PApplet p, String path, double frameTime) {
		this.p = p;
		this.path = path;
		this.frameTime = frameTime;
		curFrame = 0;
	}
	
	public void loadImages()
	{
		File path = new File(this.path);
		String url;
		int fCount = 0;
		f = path.listFiles();
		images = new PImage[f.length]; 
        //for (File file : f) 
		for(int i = 0; i < f.length; i++){
        	
            if (f[i] != null && f[i].getName().endsWith(".png"))
            {
            	url = this.path + "//" + i + ".png";
            	images[i] = p.loadImage(url, "png");
            	fCount++;
            }
            else
            {
            	System.out.println("ERROR: File of not png type.");
            }
            
        }
		
		this.fCount = fCount;
	}
	
	//Checking for swapping of next frame
	public void nextFrame() 
	{
		
		//Calculating number of frames to wait to swap frame
		int count = (int) (p.frameCount%60);
		//Making count a multiple of fCount
		//count -= count%fCount;//TODO fix animation speed
		count*=frameTime;
		//Checking if count frames have passed
		if(count%fCount == 0) 
		{
			curFrame++;
			curFrame = curFrame%fCount;
			
		}
		
	}
	
	//Returning the index of the current frame
	public int getCurFrame()
	{
		return curFrame;
	}
	
	public void displayAnimation()
	{
		System.out.println(curFrame);
		try
		{
			System.out.println("displaying");
			p.image(images[curFrame],0,0);
			nextFrame();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: Display Error.");
		}
		
	}
}