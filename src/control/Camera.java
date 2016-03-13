package control;

import processing.core.*;

public class Camera {
	
	//PApplet from the main loop
	PApplet p;
	
	//Current mouse location x/y, off set to be returned x/y, previous off set x/y
	int curX, offSetX, setX;
	int curY, offSetY, setY;
	
	//Bounds for the camera movement
	int bTop, bBot, bLeft, bRight;
	boolean bound;
	
	
	
	//mCam used for check for initial click, yMove for if y axis camera movement is enabled
	boolean mCam
	
	//Checking if x/y movement is enabled
	
	Camera(PApplet p, boolean yMove) {
		//Disambiguating variables
		this.p = p;
		this.yMove = yMove;
		
		//Initializing off set variables to 0
		offSetX = 0;
		offSetY = 0;
		setX = 0;
		setY = 0;
		
		//Initializing
		mCam = false;
		bound = false;
	}
	
	//Constructor with yMove defaulted to false
	Camera(PApplet p) {
		this(p,false);
	}
	
	//Updating the camera
	public void update(PVector off, int mode) {
		
		//Click and drag camera movement
		if(mode == 0) {
			//Checking for the initial click
			if(p.mousePressed && !mCam) {
				mCam = true;
				startLoc(off,0);
			}
			
			//Calculating the off set
			if(p.mousePressed && mCam) {
				calcOffSet(mode);
			} else {
				mCam = false;
			}
		//Mouse toward edge camera movement
		} else if(mode == 1) {
			//Checking if mouse is in border area
			if(p.mouseX < 50 || p.mouseX > p.width - 50 || p.mouseY < 50 || p.mouseY > p.height - 50) {
				
				//Checking for when mouse first enters boarder area
				if(!mCam) {
					startLoc(off,1);
					mCam = true;
				}
				
				if(mCam) {
					calcOffSet(mode);
				}
				
			} else {
				mCam = false;
			}
			
		}
	}
	
	//Get initial location
	public void startLoc(PVector off, int mode) {
		//Getting the current off set
		setX = (int) off.x;
		setY = (int) off.y;
		
		//Move toward edge camera movement
		//if(mode == 1) {
			//offSetX = setX;
			//offSetY = setY;
		//}
		
		//Getting the current position of the mouse
		curX = p.mouseX;
		curY = p.mouseY;
	}
	
	//Calculating the off set
	public void calcOffSet(int mode) {
		//Click and drag mouse movement
		if(mode == 0) {
			//Setting offset relative to old off set
			offSetX = setX + (curX - p.mouseX);
			offSetY = setY + (curY - p.mouseY);
		//Mouse toward edge camera movement
		} else if(mode == 1) {
			//System.out.println(p.mouseX + "    " + (p.width-50));
			
			if(p.mouseX < 50) {
				offSetX -= PApplet.abs(curX - p.mouseX)/2;
			} else if(p.mouseX > (p.width - 50)) {
				offSetX += PApplet.abs(curX - p.mouseX)/2;
			}
			
			//Checking if y-axis movement is enabled
			if(yMove) {
				if(p.mouseY < 50) {
					offSetY -= PApplet.abs(curY - p.mouseY);
				} else if(p.mouseY > (p.height - 50)) {
					offSetY += PApplet.abs(curY - p.mouseY);
				}
			}
			
			//Checking if X offSet has exceeded boundary's
			if(offSetX > bLeft) {
				offSetX = bLeft;
			} else if(offSetX < bRight) {
				offSetX = bRight;
			}
			
			//Checking if Y offSet has exceeded boundary's
			if(offSetY > bBot) {
				offSetY = bBot;
			} else if(offSetY < bTop) {
				offSetY = bTop;
			}
		}
	}
	
	//Sets the bounds of the camera
	public void setCameraBounds(int bTop, int bBot, int bLeft, int bRight) {
		bound = true;
		this.bTop = bTop;
		this.bBot = bBot;
		this.bLeft = bLeft;
		this.bRight = bRight;
	}
	
	//Returning the off set
	public PVector getOffSet() {
		PVector p = new PVector(offSetX,offSetY);
		return p;
	}
}
