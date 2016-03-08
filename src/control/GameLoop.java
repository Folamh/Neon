package control;

import java.util.ArrayList;

import gameObject.*;
import map.Map;
import processing.core.*;
import userInterface.*;

public class GameLoop {
	PApplet p;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	ArrayList<MenuObject> gameMenu;
	ArrayList<PVector> path;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, int gameState){
		this.p = p;
		this.gameState = gameState;
		
		gameEnemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		gameMenu = new ArrayList<MenuObject>();
		path = new ArrayList<PVector>();
		path.add(new PVector(0,p.height-50));
		
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		//Game Menu
		gameMenu.add(new Button(p,0,p.width-100,100,200,100,bImage, "Basic Turret", 40));
		gameMenu.add(new Button(p,5,p.width-100,p.height-100,200,100,bImage, "Pause", 40));
	}
	
	public void update(int gameState)
	{
		//Updating the game state
		this.gameState = gameState;
		
		//Checking the gameLoop should be updating
		if(gameState == 4)
		{
			
			if(p.frameCount%60 == 0)
			{
				gameEnemies.add(new BasicEnemy(p,p.width,p.height-50,path));
			}
			
			for(int i = 0; i < gameEnemies.size(); i++)
			{
				gameEnemies.get(i).update();
			}
			
			for(int i = 0; i < towers.size(); i++)
			{
				towers.get(i).update();
				towers.get(i).calculateTargets(gameEnemies);
				if(towers.get(i).size() != 0){
					towers.get(i).calculateLead();
				}
				
			}
			for(int i = 0; i<gameMenu.size();i++)
			{
				MenuObject o = gameMenu.get(i);
				
				o.update();
				
				if(o.getClicked())
				{
					this.gameState = ((Button)o).getValue();
					break;
				}
			}
		}
	}
	
	public void render(){
		//Checking of the gameLoop should be rendered
		if(gameState == 4 || gameState == 5) {
			for(int i = 0; i < towers.size(); i++){
				towers.get(i).render();
			}
			
			for(int i = 0; i < gameEnemies.size(); i++){
				gameEnemies.get(i).render();
			}
		}
	}
	
	//Returning the current state of the game
	public int getGameState() {
		return gameState;
	}
}
