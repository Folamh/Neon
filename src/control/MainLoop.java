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
		Animation animation = new Animation(this, "C:/Users/Haashim/Documents/Neon/Resources/Images/Enemy/Basic Enemy/Moving");
		animation.loadImages();
		
	}
	
	public void draw() {
		background(255);
		map.render();
		try{
		animation.displayAnimation();
		}
		catch(Exception e)
		{
			System.out.println("Error - ");
		}
	}

}
