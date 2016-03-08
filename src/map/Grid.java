package map;


import processing.core.*;
public class Grid {
	PApplet p;
	int startX;
	int startY;
	int widthNo;
	int heightNo;
	int gridWidth;
	int gridHeight;
	
	//PImage gridImage;
	public Grid(PApplet p, int x, int y, int widthNo, int heightNo)
	{
		this.p = p;
		startX = x;
		startY = y;
		this.widthNo = widthNo;
		this.heightNo = heightNo;
		gridWidth = 100;
		gridHeight = 100;
	}
	
	public PVector returnGrid(){
		PVector point = new PVector();
		point.set(0, 0);
		for(int i = 0; i < widthNo; i++){
			for(int j = 0; j < heightNo; j++){
				if((p.mouseX > (startX + i*gridWidth)) && (p.mouseX < (startX + gridWidth + i*gridWidth)) && (p.mouseY > (startY + j*gridHeight)) && (p.mouseY < (startY + gridHeight+ j*gridHeight))){
					point.set((startX + i*gridWidth + gridWidth/2), (startY + j*gridHeight + gridHeight/2));
				}
			}
		}
		return point;
	}
	
}
