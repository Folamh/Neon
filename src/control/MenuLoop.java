package control;

import java.util.ArrayList;

import animation.Animation;
import processing.core.*;
import userInterface.*;

public class MenuLoop {
	
	PApplet p;
	int gameState;
	
	Animation startMenu;
	
	ArrayList<MenuObject> mainMenu;
	ArrayList<MenuObject> pauseMenu;
	ArrayList<MenuObject> controlMenu;
	ArrayList<MenuObject> aboutMenu;
	
	public MenuLoop(PApplet p, int gameState) {
		//Disambiguating variables
		this.p = p;
		this.gameState = gameState;
		
		//Initializing the array lists
		mainMenu = new ArrayList<MenuObject>();
		pauseMenu = new ArrayList<MenuObject>();
		controlMenu = new ArrayList<MenuObject>();
		aboutMenu = new ArrayList<MenuObject>();
		
		//Loading the image for buttons
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		
		//TODO Initialize all the menus here
		startMenu = new Animation(p,"resources/images/menu/start", 10);
		
		mainMenu.add(new Button(p, p.width/2, 100, 300, 150, bImage, "NEON", 40));
		mainMenu.add(new Button(p, p.width/2, 250, 300, 50, bImage, "PLAY", 15));
		mainMenu.add(new Button(p, p.width/2, 310, 300, 50, bImage, "SETTINGS", 15));
		mainMenu.add(new Button(p, p.width/2, 370, 300, 50, bImage, "CREDITS", 15));
		mainMenu.add(new Button(p, p.width/2, 430, 300, 50, bImage, "EXIT", 15));
	}
	
	//Updating menus
	public void update(int gameState) {
		this.gameState = gameState;
		
		
		switch(gameState) {
			
			case 0:{
				if(p.keyPressed) {
					if(p.key == ' ') this.gameState = 1;
				}
				break;
			}
			
			//Main menu
			case 1:{
				for(int i = 0; i < mainMenu.size(); i++) {
					MenuObject o = mainMenu.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						System.out.println(((Button)o).getText());
					}
				}
				break;
			}
			
			//Controls menu
			case 2: {
				
				break;
			}
	
			//About menu
			case 3: {
				
				break;
			}
	
			//Game play shit
			case 4: {
				
				break;
			}
			
			//pause menu
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
	
	//Rendering menus
	public void render() {
		
		
		switch(gameState) {
		
			case 0:{
				p.pushMatrix();
				p.translate(p.width/2,(p.height/2)-100);
				startMenu.displayAnimation();
				p.popMatrix();
				break;
			}
			
			//Main menu
			case 1:{
				for(MenuObject o: mainMenu) {
					o.render();
				}
				break;
			}
			
			//Controls menu
			case 2: {
				
				break;
			}
	
			//About menu
			case 3: {
				
				break;
			}
	
			//Game play shit
			case 4: {
				
				break;
			}
			
			//pause menu
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
	
	//Returns the current gameState
	public int getGameState() {
		return gameState;
	}
}
