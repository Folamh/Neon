package map;


import processing.core.*;
public class Grid {
	
	PApplet p;
	PVector location;
	PVector square;
	PVector mouse;
	PImage towerPlace;
	boolean showGrid;
	
	//PImage gridImage;
	public Grid(PApplet p,boolean showGrid,PVector location,PVector square)
	{
		this.showGrid = showGrid;
		this.location = location;
		this.square = square;
		this.p = p;
		
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
	public void turretClicked()
	{
		if(p.mouseX>square.x&&p.mouseX<square.x+50&&p.mouseY>square.y&&p.mouseY<square.y+50)
		{
			if(p.mousePressed)
			{
				
			}
		}
		
	}
	
}
