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
		size(500,500);
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	Animation animation;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		Animation animation = new Animation(this, frameCount);
		animation.loadImages();
	}
	
	public void draw() {
		background(0);
		map.render();
		pushMatrix();
		for(int i = 0;i<6;i++)
		{
			image(animation.basicEnemyMoving[i],250,250);
			if(i >= 6)
			{
				i = 0;
			}
		}
		popMatrix();
	}

}
