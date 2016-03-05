package animation;

import java.io.File;
import processing.core.*;

public class Animation{
	public PImage [] images;
	PApplet p;
	File[] f;
	String path;
	double frameTime;
	//Current frame index
	public int curFrame;
	//Total number of frames
	int fCount;
	int prevTime;
	//Taking in papplet and the number of frames
	public Animation(PApplet p, String path, double frameTime) {
		this.p = p;
		this.path = path;
		this.frameTime = frameTime;
		curFrame = 0;
		loadImages();
		prevTime = 0;
	}
	
	public void loadImages()
	{
		File path = new File(this.path);
		String url;
		f = path.listFiles();
		images = new PImage[f.length]; 
        //for (File file : f) 
		for(int i = 0; i < f.length; i++){
        	
            if (f[i] != null && f[i].getName().endsWith(".png"))
            {
            	url = this.path + "//" + i + ".png";
            	//System.out.println("image " + i + " loaded successfully");
            	images[i] = p.loadImage(url, "png");
            	fCount++;
            	//System.out.println(images[i]);
            }
            else
            {
            	//System.out.println("File of not png type");
            }
            
        }
		
		//fCount--;
		System.out.println(fCount);
	}
	
	
	//Frame time is number 10ths of a second the animation will display across
	//Checking for swapping of next frame
	public void nextFrame() 
	{
		
		//Getting the current number of milliseconds
		int count = p.millis();
		
		//Making time accurate to a tenth of a second
		int time = count/100;
		
		//Calculating time to wait for each frame
		int space = PApplet.round((float) (frameTime/fCount));
		
		//Checking if count frames have passed
		if(time%space == 0 && prevTime != time) 
		{
			
			curFrame++;
			curFrame = curFrame%fCount;
			//System.out.println(curFrame);
			prevTime = time;
			
		}
		
	}
	
	//Returning the index of the current frame
	public int getCurFrame()
	{
		return curFrame;
	}
	
	public void displayAnimation()
	{
		//System.out.println(curFrame);
		try
		{
			//System.out.println(curFrame);
			p.image(images[curFrame],0,0);
			nextFrame();
		}
		catch(Exception e)
		{
			//System.out.println("Error1");
		}
		
	}
}
