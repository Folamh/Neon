package animation;

import processing.core.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

public class Animation{
	Image [] basicEnemyMoving = new Image[7];
	Image [] basicEnemyStill = new Image[6];
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
	//Method to get all image filenames
	public ArrayList<String> getAllImages(File directory, boolean descendIntoSubDirectories) 
	{
		//ArrayList that stores all the directory paths of the png images
		resultList = new ArrayList<String>(100);
        f = directory.listFiles();
        for (File file : f) {
            if (file != null && file.getName().endsWith(".png"))
            {
            	resultList.add(file.getPath());
                
            }
            if (descendIntoSubDirectories && file.isDirectory()) {
                ArrayList<String> tmp = getAllImages(file, true);
                if (tmp != null) {
                    resultList.addAll(tmp);
                }
            }
        }
        if (resultList.size() > 0)
        {
            return resultList;
        }
        else
        {
            return null;
        }
        
    }
	public Image getImage(String path)
	{
		Image tempImage = null;
		try
		{
			URL imageURL = Animation.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch(Exception e)
		{
			System.out.println("Error loading Image - " + e.getMessage());
		}
		return tempImage;
	
	}
	
	public void loadImage()
	{
		File path = new File("C:/Users/Hashdog/Documents/Neon/Resources/Images/Enemy/Basic Enemy");
		getAllImages(path,true);
		for(int i = 0;i<6;i++)
		{
			
			basicEnemyMoving[i] = getImage(resultList.get(i));
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
