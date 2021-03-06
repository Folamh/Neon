package control;

import java.util.ArrayList;
import animation.Animation;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.*;
import userInterface.*;

public class MenuLoop {
	
	PApplet p;
	Minim minim;
	int gameState;
	AudioPlayer select;
	AudioPlayer back;
	
	Animation startMenu;
	
	ArrayList<MenuObject> mainMenu;
	ArrayList<MenuObject> pauseMenu;
	ArrayList<MenuObject> settingsMenu;
	ArrayList<MenuObject> creditsMenu;
	ArrayList<MenuObject> endScreen;
	ArrayList<MenuObject> levelMenu;
	
	public int levelFlag;
	
	public MenuLoop(PApplet p, Minim minim) {
		//Disambiguating variables
		this.minim = minim;
		this.p = p;
		gameState = 0;
		
		//SFX
		select = this.minim.loadFile("Resources/Audio/SELECT.wav");
		back = this.minim.loadFile("Resources/Audio/BACK.wav");
		
		//Initializing the array lists
		mainMenu = new ArrayList<MenuObject>();
		pauseMenu = new ArrayList<MenuObject>();
		settingsMenu = new ArrayList<MenuObject>();
		creditsMenu = new ArrayList<MenuObject>();
		endScreen = new ArrayList<MenuObject>();
		levelMenu = new ArrayList<MenuObject>();
		
		//Loading the image for buttons
		PImage playImage = p.loadImage("resources/images/menu/button/0.png");
		PImage settingImage = p.loadImage("resources/images/menu/button/1.png");
		PImage creditsImage = p.loadImage("resources/images/menu/button/2.png");
		PImage exitImage = p.loadImage("resources/images/menu/button/3.png");
		PImage backImage = p.loadImage("resources/images/menu/button/4.png");
		PImage titleImage = p.loadImage("resources/images/menu/button/5.png");
		
		PImage credits = p.loadImage("resources/images/menu/button/credits.png");
		PImage blanc = p.loadImage("resources/images/menu/button/gameover.png");
		
		//TODO Initialize all the menus here
		//Start Menu
		startMenu = new Animation(p,"resources/images/menu/start", 10);
		
		//Main menu
		mainMenu.add(new Button(p, 8, p.width/2, 275, 200, 100, playImage));
		mainMenu.add(new TextArea(p, p.width/2, 100, titleImage));
		mainMenu.add(new Button(p, 3, p.width/2, 425, 200, 100, creditsImage));
		mainMenu.add(new Button(p, 6, p.width/2, 575, 200, 100, exitImage));
		
		//Pause menu
		pauseMenu.add(new TextArea(p, p.width/2, 100, 200, 100, settingImage));
		pauseMenu.add(new Button(p, 4, p.width/2, p.height-100, 200, 100, backImage));
		
		//Pause menu
		endScreen.add(new TextArea(p, p.width/2, p.height/2, p.width, p.height, blanc));
		endScreen.add(new Button(p, 1, p.width/2, p.height-100, 200, 100, backImage));
		
		//settings menu
		settingsMenu.add(new TextArea(p, p.width/2, 100, settingImage));
		settingsMenu.add(new Button(p, 1, p.width/2, p.height-100, 200, 100, backImage));
		
		//credits menu
		creditsMenu.add(new TextArea(p, p.width/2, p.height/2, 700, 700, credits));
		creditsMenu.add(new Button(p, 1, p.width/2, p.height-50, 100, 50, backImage));
		
		//level menu
		levelFlag = 0;
		levelMenu.add(new Button(p, 1, p.width/2, p.height-50, 100, 50, backImage));
		levelMenu.add(new Button(p, 2, p.width/2, 50, 100, 50, backImage));
		//TODO Add more buttons
	}
	
	//Play SELECT.wav
	void playSelect(){
		select.rewind();
		select.play();
	}
	
	//Play BACK.wav
		void playBack(){
			back.rewind();
			back.play();
		}
	
	//Updating menus
	public void update(int gameState) {
		this.gameState = gameState;
		
		//System.out.println(gameState);
		switch(this.gameState) {
			
			//Start menu
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
						if(((Button)o).getValue() != 0) {	
							this.gameState = ((Button)o).getValue();
							playSelect();
						}
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
						if(((Button)o).getValue() == 1) {
							this.gameState = ((Button)o).getValue();
							playBack();
						}
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
						if(((Button)o).getValue() == 1) {
							this.gameState = ((Button)o).getValue();
							playBack();
						}
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
				for(int i = 0; i < pauseMenu.size(); i++) {
					MenuObject o = pauseMenu.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						if(((Button)o).getValue() == 4) {
							this.gameState = ((Button)o).getValue();
							playBack();
						}
					}
				}
				break;
			}
			//Quit game
			case 6: {
				
				break;
			}
			
			case 7: {
				for(int i = 0; i < endScreen.size(); i++) {
					MenuObject o = endScreen.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						if(((Button)o).getValue() == 1) { 
							this.gameState = ((Button)o).getValue();
							playBack();
						}
					}
				}
				break;
			}
			
			//level selection menu
			case 8: {//TODO Make this work
				for(int i = 0; i < levelMenu.size(); i++) {
					MenuObject o = levelMenu.get(i);
					
					o.update();
					
					if(o.getClicked()) {
						if(((Button)o).getValue() == 1) { 
							this.gameState = ((Button)o).getValue();
							playBack();
						}
						else{
							levelFlag =((Button)o).getValue() - 1;
							this.gameState = 4;
							playSelect();
						}
					}
				}
				break;
			}
			
			default: {
				
				break;
			}
		}
		
	}
	
	//Rendering menus
	public void render() {
		
		
		switch(this.gameState) {
		
			case 0:{
				p.pushMatrix();
				p.translate(p.width/2,(p.height/2)-100);
				startMenu.displayAnimation();
				p.popMatrix();
				break;
			}
			
			//Main menu
			case 1:{
				//Going through menu objects in the mainMenu
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
				for(int i = 0; i < pauseMenu.size(); i++) {
					MenuObject o = pauseMenu.get(i);
					
					o.render();
				}
				break;
			}
	
			case 6: {
				
				break;
			}
			
			case 7: {
				for(int i = 0; i < endScreen.size(); i++) {
					MenuObject o = endScreen.get(i);
					
					o.render();
				}
				break;
			}
			
			//level menu
			case 8: {
				for(int i = 0; i < levelMenu.size(); i++) {
					MenuObject o = levelMenu.get(i);
					
					o.render();
				}
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
