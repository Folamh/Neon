package map;

import processing.core.*;
public class Grid {
	PApplet p;
	//Starting x and y for the grid
	int startX, startY;
	//size of each box, width of grid
	int gridSize, gridWidth, gridHeight;
	PVector off;
	
	//PImage gridImage;
	public Grid(PApplet p, PImage image, int gridSize)
	{
		this.p = p;
		startX = (p.width/2)-(image.width/2)-2; 
		startY = (p.height/2)-(image.height/2)-2;
		this.gridSize = gridSize;
		gridWidth = PApplet.ceil(image.width/gridSize);
		gridHeight = PApplet.ceil(image.height/gridSize);
		off = new PVector();
		off.set(0, 0);
	}
	
	public PVector returnGrid(PVector off){
		PVector point = new PVector();
		point.set(0, 0);
		for(int i = 0; i < gridWidth; i++){
			for(int j = 0; j < gridHeight; j++){
				if((p.mouseX+off.x > (startX + i*gridSize)) && (p.mouseX+off.x < (startX + gridSize + i*gridSize)) && (p.mouseY+off.y > (startY + j*gridSize)) && (p.mouseY+off.y < (startY + gridSize + j*gridSize))){
					point.set((startX + i*gridSize + gridSize/2), (startY + j*gridSize + gridSize/2));
				}
			}
		}
		return point;
	}
	
	public void showGrid(){
		for(int i = 0; i < gridWidth; i++){
			for(int j = 0; j < gridHeight; j++){
				p.noFill();
				p.stroke(255);
				p.rectMode(PConstants.CORNERS);
				p.rect(startX + i*gridSize, startY + j*gridSize, startX + gridSize + i*gridSize, startY + gridSize + j*gridSize);
			}
		}
	}
}
