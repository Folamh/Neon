package map;


import control.Camera;
import processing.core.*;
public class Grid {
	PApplet p;
	int startX;
	int startY;
	int widthNo;
	int heightNo;
	int gridWidth;
	int gridHeight;
	PVector off;
	
	//PImage gridImage;
	public Grid(PApplet p, PImage image, int widthNo, int heightNo)
	{
		this.p = p;
		startX = (p.width/2)-(image.width/2); 
		startY = (p.height/2)-(image.height/2);
		this.widthNo = widthNo;
		this.heightNo = heightNo;
		gridWidth = image.width/widthNo;
		gridHeight = image.height/heightNo;
		off = new PVector();
		off.set(0, 0);
	}
	
	public PVector returnGrid(PVector off){
		PVector point = new PVector();
		point.set(0, 0);
		for(int i = 0; i < widthNo; i++){
			for(int j = 0; j < heightNo; j++){
				if((p.mouseX+off.x > (startX + i*gridWidth)) && (p.mouseX+off.x < (startX + gridWidth + i*gridWidth)) && (p.mouseY+off.y > (startY + j*gridHeight)) && (p.mouseY+off.y < (startY + gridHeight+ j*gridHeight))){
					point.set((startX + i*gridWidth + gridWidth/2), (startY + j*gridHeight + gridHeight/2));
				}
			}
		}
		return point;
	}
	
	public void showGrid(){
		for(int i = 0; i < widthNo; i++){
			for(int j = 0; j < heightNo; j++){
				p.noFill();
				p.stroke(255);
				p.rectMode(PConstants.CORNERS);
				p.rect(startX + i*gridWidth, startY + j*gridHeight, startX + gridWidth + i*gridWidth, startY + gridHeight+ j*gridHeight);
			}
		}
	}
}
