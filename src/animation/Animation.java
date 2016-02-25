package animation;

import processing.core.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Animation {
	Image [] basicEnemyMoving = new Image[6];
	ArrayList<Image> basicEnemyStill = new ArrayList<Image>(5);
	PApplet p;
	File[] f;
	ArrayList<String> resultList;
	//Current frame index
	int curFrame;
	//Total number of frames
	int fCount;
	
	//Taking in papplet and the number of frames
	public Animation(PApplet p, int fCount) {
		this.p = p;
		this.fCount = fCount;
		
		curFrame = 0;
	}
	//Method to get all image filenames
	public ArrayList<String> getAllImages(File directory, boolean descendIntoSubDirectories) throws IOException 
	{
		//ArrayList that stores all the directory paths of the jpegs
		resultList = new ArrayList<String>(100);
        f = directory.listFiles();
        for (File file : f) {
            if (file != null && file.getName().toLowerCase().endsWith(".jpg"))
            {
                resultList.add(file.getCanonicalPath());
                
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
			System.out.println("Error loading Image");
		}
		return tempImage;
	
	}
	
	public void loadImage()throws IOException
	{
		String p = "Resources" + File.separator + "images" + File.separator + "Enemy";
		File path = new File(p);
		getAllImages(path,true);
		for(int i = 0;i<6;i++)
		{
			p = resultList.get(i);
			basicEnemyMoving[i] = getImage(p);
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
}
