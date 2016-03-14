package control;

import processing.core.*;

public class newCamera {

	//PApplet from the main loop
	PApplet p;
	
	//x y off set to be returned
	int offSetX, offSetY;
	
	//x y off set for adding to off set to be returned
	int tmpOffSetX, tmpOffSetY;
	
	//x y starting location for getting off set
	int startX, startY;
	
	//current x y off set
	int curOffSetX, curOffSetY;
	
	//x y movement enablers
	boolean moveX, moveY;
	
	//drag push movement enabler
	boolean drag, push;
	
	//drag checker
	boolean draggedX, draggedY;
	
	//edge border for pushing the camera
	int border;
	
	//camera x y boundary enabler
	boolean boundX, boundY;
	
	//camera boundary
	int minX, maxX, minY, maxY;
	
	//Initializing the camera
	public newCamera(PApplet p) {
		//setting papplet
		this.p = p;
		
		//Initializing booleans
		moveX = true;
		moveY = true;
		drag = true;
		push = true;
		
		draggedX = false;
		draggedY = false;
		
		boundX = false;
		boundY = false;
		
		//Initializing integers
		offSetX = 0;
		offSetY = 0;
		tmpOffSetX = 0;
		tmpOffSetY = 0;
		startX = 0;
		startY = 0;
		curOffSetX = 0;
		curOffSetY = 0;
		border = 0;
		minX = 0;
		maxX = 0;
		minY = 0;
		maxY = 0;
	}
	
	//calculating the off set
	public void calcOffSet() {
		//Setting the current off set
		offSetX = curOffSetX;
		offSetY = curOffSetY;
		
		//if x movement is enabled
		if(moveX) {
			//checking if screen drag is enabled
			if(drag) {
				//checking if the screen is being dragged
				if(p.mousePressed && !draggedX) {
					//holding the current off set at time of click
					tmpOffSetX = curOffSetX;
					draggedX = true;
				} else if(p.mousePressed && draggedX){
					//setting the off set while mouse is dragged
					offSetX = tmpOffSetX - (startX - p.mouseX);
				} else {
					//setting the current start loc
					startX = p.mouseX;
					draggedX = false;
				}
			}
			//checking if screen push is enabled
			if(push) {
				//checking if mouse past borders
				if(p.mouseX < border) {
					//increasing off set
					offSetX += (border - p.mouseX);
				} else if(p.mouseX > p.width - border) {
					//decreasing off set
					offSetX += (p.width-border) - p.mouseX;
				}
			}
		}
		
		//if y movement is enabled
		if(moveY) {
			//checking if screen drag is enabled
			if(drag) {
				//checking if the screen is being dragged
				if(p.mousePressed && !draggedY) {
					//holding the current off set at time of click
					tmpOffSetY = curOffSetY;
					draggedY = true;
				} else if(p.mousePressed && draggedY){
					//setting the off set while mouse is dragged
					offSetY = tmpOffSetY - (startY - p.mouseY);
				} else {
					//setting the current start loc
					startY = p.mouseY;
					draggedY = false;
				}
			}
			//checking if screen push is enabled/
			if(push) {
				//checking if mouse past borders
				if(p.mouseY < border) {
					//increasing off set
					offSetY += (border - p.mouseY);
				} else if(p.mouseY > p.height - border) {
					//decreasing off set
					offSetY += (p.height-border) - p.mouseY;
				}
			}
		}
		
		//Checking if the x off set is bound
		if(boundX) {
			//checking if the off set has exceeded its bounds
			if(offSetX < minX) {
				offSetX = minX;
			} else if(offSetX > maxX) {
				offSetX = maxX;
			}
		}
		
		//checking if the y off set is bound
		if(boundY) {
			//checking if the off set has exceeded its bounds
			if(offSetY < minY) {
				offSetY = minY;
			} else if(offSetY > maxY) {
				offSetY = maxY;
			}
		}
	}
	
	//setting x movement
	public void setMoveX(boolean moveX) {
		this.moveX = moveX;
	}
	
	//setting y movement
	public void setMoveY(boolean moveY) {
		this.moveY = moveY;
	}
	
	//setting push movement
	public void setPush(boolean push) {
		this.push = push;
	}
	
	//setting the push border
	public void setPushBorder(int border) {
		this.border = border;
	}
	
	//setting drag movement
	public void setDrag(boolean drag) {
		this.drag = drag;
	}
	
	//setting x binding
	public void setBoundX(boolean boundX) {
		this.boundX = boundX;
	}
	
	//setting y binding
	public void setBoundY(boolean boundY) {
		this.boundY = boundY;
	}
	
	//setting the x binding dimensions
	public void setBoundXDim(int minX, int maxX) {
		this.minX = minX;
		this.maxX = maxX;
	}
	
	//setting the y binding dimension
	public void setBoundYDim(int minY, int maxY) {
		this.minY = minY;
		this.maxY = maxY;
	}
	
	//setting the current off set
	public void setCurOffSet(PVector curOffSet) {
		curOffSetX = (int) curOffSet.x;
		curOffSetY = (int) curOffSet.y;
	}
	
	//returning the calculated off set
	public PVector getOffSet() {
		return new PVector(offSetX, offSetY);
	}
}