package control;

import processing.core.*;
import java.util.ArrayList;

import animation.Animation;
import gameObject.BasicEnemy;
import gameObject.Enemy;

public class MainLoop extends PApplet{
	
	//DO NOT CHANGE THIS UNDER ANY CIRCUMSTANCES!!!!!!!!!
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new MainLoop());
	}
	
	//Settings go here
	public void settings() {
		//fullScreen();
		//Set to 720p resolution
		size(1280,720);
	}
	
	//ABOVE THIS LINE SHOULDNT CHANGE
	
	//Variables
	PImage background;
	MenuLoop menuLoop;
	GameLoop gameLoop;
	
	Enemy enemy;
	ArrayList<PVector> path;
	
	int gameState;
	
	
	//Only use this for initializing variables
	public void setup() {
		//Initializing the frame rate and the image mode
		frameRate(60);
		imageMode(CENTER);
		
		path = new ArrayList<PVector>();
		path.add(new PVector(100,height/2));
		
		//This will be removed
		enemy = new BasicEnemy(this,width-100,height/2,path);
		
		//Initializing the gameState to 0
		gameState = 0;
		
		//Loading the background image
		background = loadImage("resources/images/backgrounds/0.png");
		
		//Initializing menu and game loops
		menuLoop = new MenuLoop(this, gameState);
		gameLoop = new GameLoop(this, gameState);
	}
	
	public void update(){
		//Printing the framerate of the program
		//System.out.println(frameRate);
		
		//Resizing the background for the current window size
		background.resize(width,height);
		enemy.update();
		
		menuLoop.update(gameState);
		//gameLoop.update();
		
		gameState = menuLoop.getGameState();
		
		if(gameLoop.getGameState() == 5) {
			
		}
	}
	
	public void render(){
		//Rendering the background
		image(background,width/2,height/2);
		
		menuLoop.render();
		//gameLoop.render();
		
	}
	
	public void draw() {
		//background(0);
		//map.render();
		update();
		render();
	}
}
