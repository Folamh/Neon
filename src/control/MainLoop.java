
package control;

import processing.core.*;

public class MainLoop extends PApplet{
	
	//DO NOT CHANGE THIS UNDER ANY CIRCUMSTANCES!!!!!!! Actually do, it will be fun!
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new MainLoop());
	}
	
	//Settings go here
	public void settings() {
		//fullScreen();
		size(1000,1021);
	}
	
	int gameState;
	
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		gameState = 0;
		imageMode(CENTER);
	}
	
	public void draw() {
		switch(gameState){
		case 0://Start Menu
			break;
		case 1://Main Menu
			break;
		case 2://GameLoop
			break;
		}
	}

}
