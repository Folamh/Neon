package animation;

import java.util.ArrayList;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import javax.imageio.*;

public class Animation 
{
	
	ArrayList<Image> images = new ArrayList<Image>();
	public void loadImages()
	{
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Error");
		}
	}
	
	public void runAnimation()
	{
		
	}
	
	public void chooseAnimation()
	{
		
	}

}
