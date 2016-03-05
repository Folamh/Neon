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
	Animation startMenu;
	PImage background;
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
		
		enemy = new BasicEnemy(this,width-100,height/2,path);
		
		gameState = 0;
		
		background = loadImage("resources/images/backgrounds/0.png");
		
		startMenu = new Animation(this,"resources/images/menu/start", 10);
	}
	
	public void update(){
		//Printing the framerate of the program
		//System.out.println(frameRate);
		
		//Resizing the background for the current window size
		background.resize(width,height);
		enemy.update();
		switch(gameState) {
			case 0:{
				break;
			}
			
			case 1:{
			
				break;
			}
			
			case 2: {
				
				break;
			}
	
			case 3: {
				
				break;
			}
	
			case 4: {
				
				break;
			}
	
			case 5: {
				
				break;
			}
	
			case 6: {
				
				break;
			}
			
			default: {
				
				break;
			}
		}
	}
	
	public void render(){
		//Rendering the background
		image(background,width/2,height/2);
		
		switch(gameState) {
			case 0:{
				
				pushMatrix();
				translate(width/2,(height/2)-100);
				startMenu.displayAnimation();
				popMatrix();
				enemy.render();
				break;
			}
			
			case 1:{
			
				break;
			}
			
			case 2: {
				
				break;
			}
	
			case 3: {
				
				break;
			}
	
			case 4: {
				
				break;
			}
	
			case 5: {
				
				break;
			}
	
			case 6: {
				
				break;
			}
			
			default: {
				
				break;
			}
		}
		
	}
	
	public void draw() {
		//background(0);
		//map.render();
		update();
		render();
	}
}
