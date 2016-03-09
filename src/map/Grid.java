package map;

import java.util.ArrayList;

import processing.core.*;
public class Grid {
	PApplet p;
	//Starting x and y for the grid
	int startX, startY;
	//size of each box, width of grid
	int gridSize, gridWidth, gridHeight, gridCount;
	PVector off;
	boolean display;
	
	//PImage gridImage;
	public Grid(PApplet p, PImage image, int gridSize)
	{
		this.p = p;
		startX = (p.width/2)-(image.width/2)-1; 
		startY = (p.height/2)-(image.height/2)-1;
		this.gridSize = gridSize;
		gridWidth = PApplet.ceil(image.width/gridSize);
		gridHeight = PApplet.ceil(image.height/gridSize);
		gridCount = gridWidth/gridHeight;
		
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
	
	public PVector returnPoint(int i, int j) {
		PVector point = new PVector();
		
		point.set((startX + i*gridSize + gridSize/2), (startY + j*gridSize + gridSize/2));
		
		return point;
	}
	
	public void showGrid(ArrayList<PVector> gridUsed){
		for(int i = 0; i < gridWidth; i++){
			for(int j = 0; j < gridHeight; j++){
				
				PVector point = returnPoint(i,j);
				
				System.out.println();
				
				for(PVector g: gridUsed) {
					System.out.println(g + " " + point);
					if(point == g) {
						display = false;
						break;
					} else {
						display = true;
					}
				}
				
				if(display) {
					p.noFill();
					p.stroke(255);
					p.rectMode(PApplet.CENTER);
					p.rect(point.x,point.y,gridSize,gridSize);
				}
			}
		}
	}
	
	//Returns the width of the grid
	public int getGridWidth() {
		return gridWidth;
	}
	
	//Returns the height of the grid
	public int getGridHeight() {
		return gridHeight;
	}
	
	//Returns the count inside the grid
	public int getGridCount() {
		return gridCount;
	}
}
