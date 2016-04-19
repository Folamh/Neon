
package control;

import java.io.File;

import ddf.minim.Minim;
import map.Level;
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
		size(1280,720, JAVA2D);
	}
	
	//ABOVE THIS LINE SHOULDNT CHANGE
	
	//Variables
	PImage background;
	MenuLoop menuLoop;
	GameLoop gameLoop;
	Minim minim;
	int gameState;
	Music music;
	File[] levelFiles;
	boolean playingLevel;
	int levelFlag;
	
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
		
		//Setting up music
		minim = new Minim(this);
		music = new Music(this);

		//Initializing menu
		menuLoop = new MenuLoop(this, minim);
		
		//Setup of level files
		File levelPath = new File("Resources\\Levels");
		levelFiles = levelPath.listFiles();
		levelFlag = 0;
	}
	
	public void update(){
		//Printing the frame rate of the program
		//System.out.println(frameRate);
		
		menuLoop.update(gameState);
		
		//Updating the game state form the menuLoop
		gameState = menuLoop.getGameState();
		
		//Return level
		//TODO set the levelFlag from the menu
		if(levelFlag != 0){
			Level level = new Level(levelFiles[levelFlag]);
			gameLoop = new GameLoop(this, minim, gameState, level);
			levelFlag = 0;
			playingLevel = true;
		}
		if(playingLevel){
			gameLoop.update(gameState);
			
			if(gameLoop.getGameState() == 5 || gameLoop.getGameState() == 7) {
				gameState = gameLoop.getGameState();
			}
		}
		
		//System.out.println(gameState);
		
		//Checking if the player paused the game
		
		
		//Music player
		music.doShit(gameState);
	}
	
	public void render(){
		//Rendering the background
		image(background,width/2,height/2);
		if(playingLevel){
			gameLoop.render();
		}
		menuLoop.render();
		
	}
	
	public void draw() {
		if(gameState != 6) {
			update();
			render();
		} else {
			exit();
		}
	}
}
