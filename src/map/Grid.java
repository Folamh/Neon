package map;


import processing.core.*;
public class Grid {
	
	PApplet p;
	PVector location;
	PVector square;
	boolean showGrid;
	
	//PImage gridImage;
	public Grid(boolean showGrid,PVector location,PVector square)
	{
		this.showGrid = showGrid;
		this.location = location;
		this.square = square;

		
	}
	public void loadGrid()
	{
	}
	
	public PVector displayGrid()
	{
		
		for(int i = 0;i < location.x; i++)
		{
			
			for(int j = 0; j< location.y;j++)
			{
				if(square.x > i*100)
				{
					square.y += 100;
					
				}
				square.x += 100;
			}
		}
		return square;
	}
	
}
