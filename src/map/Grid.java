package map;

import processing.core.*;
public class Grid {
	
	PApplet p;
	PVector location;
	boolean showGrid;
	PImage gridImage;
	public Grid(boolean showGrid,PVector location)
	{
		this.showGrid = showGrid;
		this.location = location;

		
	}
	public void loadGrid()
	{
		gridImage = p.loadImage("resources\\Images\\Grid\\0.png");
	}
	
	public void displayGrid()
	{
		
		p.image(gridImage, location.x,location.y);
		
	}
	
}
