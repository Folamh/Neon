package map;

import processing.core.*;
public class Grid {
	
	PApplet p;
	int turretLocation;
	boolean showGrid;
	PImage gridImage;
	Grid(int turretLocation, boolean showGrid)
	{
		this.turretLocation = turretLocation;
		this.showGrid = showGrid;
		if(showGrid)
		{
			loadGrid();
		}
	}
	
	void loadGrid()
	{
		p.loadImage("Resources/Images/Grid/GridTemplate.png");
		p.image(gridImage, p.width/2,0);
		
	}
}
