package control;

import processing.core.*;

import java.util.ArrayList;

import animation.Animation;
import gameObject.*;
import map.*;

public class MainLoop extends PApplet{
	
	//DO NOT CHANGE THIS UNDER ANY CIRCUMSTANCES!!!!!!!!!
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new MainLoop());
	}
	
	//Settings go here
	public void settings() {
		fullScreen();
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	Animation animation;
	//Only use this for initilising variables
	public void setup() {
		map.loadMap("Resources\\Maps\\map1.txt");
		tiles = map.getTiles();
		Animation animation = new Animation(this, frameCount);
		animation.loadImage();
	}
	
	public void draw() {
		background(0);
		map.render();
		animation.getCurFrame();
		println(animation.curFrame);
		
	}

}
