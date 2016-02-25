package animation;

import processing.core.*;

public class Animation {
	
	PApplet p;
	
	//Current frame index
	int curFrame;
	//Total number of frames
	int fCount;
	
	//Taking in papplet and the number of frames
	Animation(PApplet p, int fCount) {
		this.p = p;
		this.fCount = fCount;
		
		curFrame = 0;
	}
	
	//Checking for swapping of next frame
	public void nextFrame() {
		//Calculating number of frames to wait to swap frame
		int count = (int) (p.frameCount%p.frameRate);
		//Making count a multiple of fCount
		count -= count%fCount;
		
		//Checking if count frames have passed
		if(count%fCount == 0) {
			curFrame++;
			curFrame = curFrame%fCount;
		}
	}
	
	//Returning the index of the current frame
	public int getCurFrame() {
		return curFrame;
	}
}
