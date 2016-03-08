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
	ArrayList<MenuObject> settingsMenu;
	ArrayList<MenuObject> creditsMenu;
	
	public MenuLoop(PApplet p, int gameState) {
		//Disambiguating variables
		this.p = p;
		this.gameState = gameState;
		
		//Initializing the array lists
		mainMenu = new ArrayList<MenuObject>();
		pauseMenu = new ArrayList<MenuObject>();
		settingsMenu = new ArrayList<MenuObject>();
		creditsMenu = new ArrayList<MenuObject>();
		
		//Loading the image for buttons
		PImage playImage = p.loadImage("resources/images/menu/button/0.png");
		PImage settingImage = p.loadImage("resources/images/menu/button/1.png");
		PImage creditsImage = p.loadImage("resources/images/menu/button/2.png");
		PImage exitImage = p.loadImage("resources/images/menu/button/3.png");
		PImage backImage = p.loadImage("resources/images/menu/button/4.png");
		
		//TODO Initialize all the menus here
		//Start Menu
		startMenu = new Animation(p,"resources/images/menu/start", 10);
		
		//Main menu
		mainMenu.add(new Button(p, 4, p.width/2, 275, 200, 100, playImage, "", 15));
		mainMenu.add(new Button(p, 2, p.width/2, 400, 200, 100, settingImage, "", 15));
		mainMenu.add(new Button(p, 3, p.width/2, 525, 200, 100, creditsImage, "", 15));
		mainMenu.add(new Button(p, 6, p.width/2, 650, 200, 100, exitImage, "", 15));
		
		//settings menu
		settingsMenu.add(new Button(p, 0, p.width/2, 100, settingImage, "", 15));
		settingsMenu.add(new Button(p, 1, p.width/2, p.height-100, 200, 100, backImage, "", 15));
		
		//settings menu
		creditsMenu.add(new Button(p, 0, p.width/2, 100, creditsImage, "", 15));
		creditsMenu.add(new Button(p, 1, p.width/2, p.height-100, 200, 100, backImage, "", 15));
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
						this.gameState = ((Button)o).getValue();
						break;
					}
				}
				break;
			}
			
			//Controls menu
			case 2: {
				for(int i = 0; i < settingsMenu.size(); i++) {
					MenuObject o = settingsMenu.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						if(((Button)o).getValue() == 1) this.gameState = ((Button)o).getValue();
					}
				}
				break;
			}
	
			//About menu
			case 3: {
				for(int i = 0; i < creditsMenu.size(); i++) {
					MenuObject o = creditsMenu.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						if(((Button)o).getValue() == 1) this.gameState = ((Button)o).getValue();
					}
				}
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
			//Quit game
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
				for(int i = 0; i < settingsMenu.size(); i++) {
					MenuObject o = settingsMenu.get(i);
					
					o.render();
				}
				break;
			}
	
			//About menu
			case 3: {
				for(int i = 0; i < creditsMenu.size(); i++) {
					MenuObject o = creditsMenu.get(i);
					
					o.render();
				}
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
