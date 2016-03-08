package control;

import ddf.minim.Minim;
import music.Music;
import processing.core.*;

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
	Minim minim;
	int gameState;
	Music music;
	
	//Only use this for initializing variables
	public void setup() {
		//Initializing the frame rate and the image mode
		frameRate(60);
		imageMode(CENTER);
		
		//Initializing the gameState to 0
		gameState = 0;
		
		//Loading the background image
		background = loadImage("resources/images/backgrounds/background/0.png");
		
		//Resizing the background for the current window size
		background.resize(width,height);
		
		//Initializing menu and game loops
		menuLoop = new MenuLoop(this, gameState);
		gameLoop = new GameLoop(this, gameState);
		
		//Setting up music
		minim = new Minim(this);
		music = new Music(this);
	}
	
	public void update(){
		//Printing the frame rate of the program
		//System.out.println(frameRate);
		
		menuLoop.update(gameState);
		gameLoop.update(gameState);
		
		//Updating the game state form the menuLoop
		gameState = menuLoop.getGameState();
		
		//Checking if the player paused the game
		if(gameLoop.getGameState() == 5) {
			gameState = gameLoop.getGameState();
		}
		
		//Music player
		music.doShit(gameState);
	}
	
	public void render(){
		//Rendering the background
		image(background,width/2,height/2);
		
		menuLoop.render();
		gameLoop.render();
		
	}
	
	public void draw() {
		//background(0);
		//map.render();
		System.out.println(gameState);
		if(gameState != 6) {
			update();
			render();
		} else {
			exit();
		}
	}
}
