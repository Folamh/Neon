package animation;

import java.io.File;


import processing.core.*;

public class Animation extends PApplet{
	public PImage [] images;
	PApplet p;
	File[] f;
	String path;
	//Current frame index
	public int curFrame;
	//Total number of frames
	int fCount;
	//Taking in papplet and the number of frames
	public Animation(PApplet p, String path) {
		this.p = p;
		this.path = path;
		
		curFrame = 0;
	}
	
	public void loadImages()
	{
		sketchPath("");
		System.out.println(sketchPath(""));
		File path = new File(this.path);
		String url;
		int fCount = 0;
		f = path.listFiles();
		images = new PImage[f.length]; 
        //for (File file : f) 
		for(int i = 0; i < f.length; i++){
        	
        	
            if (f[i] != null && f[i].getName().endsWith(".png"))
            {
            	url = "Resources/Images/Enemy/Basic Enemy/Moving/" + i + ".png";
            	System.out.println("image " + i + " loaded successfully");
            	images[i] = p.loadImage(url, "png");
            	fCount++;
            	System.out.println(images[i]);
            }
            else
            {
            	System.out.println("Error");
            }
            
        }
		
		this.fCount = fCount;
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
			p.image(images[curFrame],100,100);
			
		}
		catch(Exception e)
		{
			System.out.println("Error1");
		}
		
	}
}
